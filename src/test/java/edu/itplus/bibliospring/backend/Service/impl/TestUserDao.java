package edu.itplus.bibliospring.backend.Service.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserRepository;

import java.util.List;

public class TestUserDao {
    public User nonDbUser;
    public User dbUser;
    public TestUserDao() {
        nonDbUser = new User();
        nonDbUser.setUsername("Helo");
        nonDbUser.setPassword(TestPasswordEncrypter.password);
        nonDbUser.setUUID(TestPasswordEncrypter.salt);
        nonDbUser.setId(1L);

        dbUser = new User();
        dbUser.setUsername("Helo");
        dbUser.setPassword(TestPasswordEncrypter.hashedPassword);
        dbUser.setUUID(TestPasswordEncrypter.salt);
        dbUser.setId(1L);
    }
}
