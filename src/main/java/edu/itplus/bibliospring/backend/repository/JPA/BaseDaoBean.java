package edu.itplus.bibliospring.backend.repository.JPA;

import edu.itplus.bibliospring.backend.model.AbstractModel;
import edu.itplus.bibliospring.backend.repository.BaseDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class BaseDaoBean<T extends AbstractModel,I> implements BaseDao<T,I> {
    @PersistenceContext
    private EntityManager em;
    private Class<T> type;
    public BaseDaoBean(Class<T> type) {
        this.type = type;
    }
    @Override
    public T findById(I id) {
        return em.find(type,id);
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public List<T> findAll() {
        return List.of();
    }
}
