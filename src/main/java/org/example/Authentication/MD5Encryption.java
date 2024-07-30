package org.example.Authentication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryption {

    public MD5Encryption() {
    }

    //encrypt password using MD5
    public String encryptedPassword(String password)throws NoSuchAlgorithmException {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(password.getBytes());
        StringBuffer encryptedPassword = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            encryptedPassword.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return encryptedPassword.toString();
    }
}
