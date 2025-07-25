package edu.itplus.bibliospring.backend.Service.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.JDBC.JdbcDao;
import edu.itplus.bibliospring.backend.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public class TestUserDao implements UserRepository {
    private User user;
    public TestUserDao() {
        this.user = new User();
        user.setUsername("Helo");
        user.setPassword("1234");
        user.setUUID("salt");
        user.setId(1L);
    }
    @Override
    public User findById(Long id) {
        if(user.getId().equals(id)) {
            return user;
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
        return List.of(new User[]{user});
    }

    @Override
    public User findByUsername(String username) {
        if(user.getUsername().equals(username)) {
            return user;
        }
        return null;
    }
}
