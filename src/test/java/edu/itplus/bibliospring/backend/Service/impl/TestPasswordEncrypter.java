package edu.itplus.bibliospring.backend.Service.impl;

import edu.itplus.bibliospring.backend.Utils.PasswordEncrypter;

public class TestPasswordEncrypter implements PasswordEncrypter {
    public static String password="123";
    public static String salt="salt";
    public static String hashPassword="hashPassword";
    @Override
    public String hashPassword(String password, String salt) {
        if(password.equals(TestPasswordEncrypter.password) && password.equals(salt)) {
            return hashPassword;
        }else throw new IllegalArgumentException("Invalid password");
    }
}
