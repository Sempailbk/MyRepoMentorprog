package edu.itplus.bibliospring.backend.Service.impl;

import edu.itplus.bibliospring.backend.Utils.PasswordEncrypter;

public class TestPasswordEncrypter implements PasswordEncrypter {
    @Override
    public String hashPassword(String password, String salt) {
        if(password.equals("1234") && password.equals(salt)) {
            return "hashed1234";
        }else throw new IllegalArgumentException("Invalid password");
    }
}
