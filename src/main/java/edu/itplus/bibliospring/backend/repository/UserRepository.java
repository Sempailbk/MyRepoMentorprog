package edu.itplus.bibliospring.backend.repository;

import edu.itplus.bibliospring.backend.model.User;

import java.util.List;

public interface UserRepository {
    User findById(Long id);
    User create(User user);
    void update(User user);
    void delete(User user);
    List<User> findAll();
    User findByUsername(String username);
}
