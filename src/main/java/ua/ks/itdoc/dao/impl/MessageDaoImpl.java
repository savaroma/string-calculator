package ua.ks.itdoc.dao.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.ks.itdoc.dao.MessageDao;
import ua.ks.itdoc.model.Message;
import ua.ks.itdoc.model.User;
import ua.ks.itdoc.util.GravatarUtil;

@SuppressWarnings("ALL")
@Repository
public class MessageDaoImpl implements MessageDao {

    private static final String GRAVATAR_DEFAULT_IMAGE_TYPE = "monsterid";
    private static final int GRAVATAR_SIZE = 48;
    private NamedParameterJdbcTemplate template;

    @Autowired
    public MessageDaoImpl(DataSource ds) {
        template = new NamedParameterJdbcTemplate(ds);
    }

    @Override
    public List<Message> getUserTimelineMessages(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());

        String sql = "SELECT message.*, user.* FROM message, user WHERE " +
                "user.user_id = message.author_id AND user.user_id = :id " +
                "ORDER BY message.pub_date DESC";
        List<Message> result = template.query(sql, params, messageMapper);

        return result;
    }

    @Override
    public List<Message> getUserFullTimelineMessages(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());

        String sql = "SELECT message.*, user.* FROM message, user " +
                "WHERE message.author_id = user.user_id AND ( " +
                "user.user_id = :id OR " +
                "user.user_id IN (SELECT followee_id FROM follower " +
                "WHERE follower_id = :id))" +
                "ORDER BY message.pub_date DESC";
        List<Message> result = template.query(sql, params, messageMapper);

        return result;
    }

    @Override
    public List<Message> getPublicTimelineMessages() {
        Map<String, Object> params = new HashMap<>();

        String sql = "SELECT message.*, user.* FROM message, user " +
                "WHERE message.author_id = user.user_id " +
                "ORDER BY message.pub_date DESC";
        List<Message> result = template.query(sql, params, messageMapper);

        return result;
    }

    @Override
    public void insertMessage(Message m) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", m.getUserId());
        params.put("text", m.getText());
        params.put("result", m.getCalculate());
        params.put("pubDate", m.getPubDate());

        String sql = "INSERT INTO message (author_id, text, result, pub_date) VALUES (:userId, :text, :result, :pubDate)";
        template.update(sql, params);
    }

    private RowMapper<Message> messageMapper = (rs, rowNum) -> {
        Message m = new Message();

        m.setId(rs.getInt("message_id"));
        m.setUserId(rs.getInt("author_id"));
        m.setUsername(rs.getString("username"));
        m.setText(rs.getString("text"));
        m.setResult(rs.getString("result"));
        m.setPubDate(rs.getTimestamp("pub_date"));
        try {
            m.setGravatar(GravatarUtil.gravatarURL(rs.getString("email"), GRAVATAR_DEFAULT_IMAGE_TYPE, GRAVATAR_SIZE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return m;
    };

}