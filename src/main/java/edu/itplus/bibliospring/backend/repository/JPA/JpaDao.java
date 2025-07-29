package edu.itplus.bibliospring.backend.repository.JPA;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JPA")
public class JpaDao extends BaseDaoBean<User,Long> implements UserRepository {


    public JpaDao() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
