package edu.itplus.bibliospring.backend.repository;

import edu.itplus.bibliospring.backend.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends BaseDao<User,Long>{

    User findByUsername(String username);
}
