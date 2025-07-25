package edu.itplus.bibliospring.backend.repository;

import edu.itplus.bibliospring.backend.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository {
    User findById(Long id);
    User create(User user);
    void update(User user);
    void delete(User user);
    List<User> findAll();
    User findByUsername(String username);
}
