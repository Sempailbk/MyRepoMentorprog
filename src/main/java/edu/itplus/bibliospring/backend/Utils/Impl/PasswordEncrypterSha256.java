package edu.itplus.bibliospring.backend.Utils.Impl;

import edu.itplus.bibliospring.backend.Utils.PasswordEncrypter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Repository
@Profile("JDBC")
public class PasswordEncrypterSha256 implements PasswordEncrypter {
    public String hashPassword(String password, String salt) {
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
