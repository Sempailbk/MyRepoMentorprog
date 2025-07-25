package edu.itplus.bibliospring.backend.Service.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserRepository;

import java.util.List;

public class TestUserDao implements UserRepository {
    public static User nonDbUser;
    public static User dbUser;
    public TestUserDao() {
        this.nonDbUser = new User();
        nonDbUser.setUsername("Helo");
        nonDbUser.setPassword(TestPasswordEncrypter.password);
        nonDbUser.setUUID(TestPasswordEncrypter.salt);
        nonDbUser.setId(1L);

        this.dbUser = new User();
        dbUser.setUsername("Helo");
        dbUser.setPassword(TestPasswordEncrypter.password);
        dbUser.setUUID(TestPasswordEncrypter.salt);
        dbUser.setId(1L);
    }

    @Override
    public User findById(Long id) {
        if(nonDbUser.getId().equals(id)) {
            return nonDbUser;
        }
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> findAll() {
        return List.of(new User[]{nonDbUser});
    }

    @Override
    public User findByUsername(String username) {
        if(nonDbUser.getUsername().equals(username)) {
            return nonDbUser;
        }
        return null;
    }
}
