package edu.itplus.bibliospring.backend.repository.JPA;

import com.sun.jdi.PrimitiveValue;
import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.RepositoryException;
import edu.itplus.bibliospring.backend.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JPA")
public class JpaDao extends BaseDaoBean<User,Long> implements UserRepository {
    @Autowired
    private Logger log;

    public JpaDao() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        try{
            TypedQuery<User> query = em.createQuery("FROM User u WHERE u.username = :userName", User.class);
            query.setParameter("userName", username);
            return query.getSingleResult();
        } catch (PersistenceException e){
            log.error("FindByUsername failed",e);
            throw new RepositoryException("FindByUsername failed",e);
        }
    }
}
