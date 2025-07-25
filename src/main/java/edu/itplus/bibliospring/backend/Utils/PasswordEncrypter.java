package edu.itplus.bibliospring.backend.Utils;

import org.springframework.stereotype.Component;

@Component
public interface PasswordEncrypter {
    String hashPassword(String password, String salt);
}
