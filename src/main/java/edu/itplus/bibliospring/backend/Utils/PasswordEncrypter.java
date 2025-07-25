package edu.itplus.bibliospring.backend.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Component
public class PasswordEncrypter {
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] input = (password + salt).getBytes();
            md.reset();
            md.update(input);

            byte[] output = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte out : output) {
                sb.append(String.format("%02X",out));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
