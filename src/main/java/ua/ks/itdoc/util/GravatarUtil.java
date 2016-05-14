package ua.ks.itdoc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GravatarUtil {
    private static String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte anArray : array) {
            sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    private static String md5Hex(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return hex(md.digest(message.getBytes("CP1252")));
        //return null;
    }

    public static String gravatarURL(String email, String defaultImage, int size) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return String.format(
                "http://www.gravatar.com/avatar/%s?d=%s&s=%d",
                md5Hex(email),
                defaultImage,
                size
        );
    }
}