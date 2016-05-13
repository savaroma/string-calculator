package ua.ks.itdoc.dao;

import java.util.List;

import ua.ks.itdoc.model.Message;
import ua.ks.itdoc.model.User;


public interface MessageDao {
    List<Message> getUserTimelineMessages(User user);

    List<Message> getUserFullTimelineMessages(User user);

    List<Message> getPublicTimelineMessages();

    void insertMessage(Message m);
}