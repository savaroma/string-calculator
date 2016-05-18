package ua.ks.itdoc.model;

import java.util.regex.Pattern;

import spark.utils.StringUtils;

public class User {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern EMAIL_ADDRESS_REGEX = Pattern.compile(EMAIL_PATTERN);


    private int id;
    private String username;
    private String email;
    private String password;
    private String password2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String validate() {
        String error = null;

        if (StringUtils.isEmpty(username)) {
            error = "You have to enter a username";
        } else if (!EMAIL_ADDRESS_REGEX.matcher(email).matches()) {
            error = "You have to enter a valid email address";
        } else if (StringUtils.isEmpty(password)) {
            error = "You have to enter a password";
        } else if (!password.equals(password2)) {
            error = "The two passwords do not match";
        }

        return error;
    }
}