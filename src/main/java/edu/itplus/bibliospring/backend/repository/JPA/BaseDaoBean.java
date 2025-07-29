package edu.itplus.bibliospring.backend.repository.JPA;

import edu.itplus.bibliospring.backend.model.AbstractModel;
import edu.itplus.bibliospring.backend.repository.BaseDao;
import edu.itplus.bibliospring.backend.repository.RepositoryException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class BaseDaoBean<T extends AbstractModel,I> implements BaseDao<T,I> {
    @PersistenceContext
    protected EntityManager em;
    private Class<T> type;
    @Autowired
    private Logger log;

    public BaseDaoBean(Class<T> type) {
        this.type = type;
    }

    @Override
    public T findById(I id) {
        try{
            return em.find(type,id);
        } catch (PersistenceException e){
            log.error("FindById failed",e);
            throw new RepositoryException("FindById failed",e);
        }
    }

    @Override
    public T create(T entity) {
        try{
            em.persist(entity);
            em.flush();
            return entity;
        } catch (PersistenceException e){
            log.error("Create failed",e);
            throw new RepositoryException("Create failed",e);
        }
    }

    @Override
    public void update(T entity) {
        try{
            em.merge(entity);
        } catch (PersistenceException e){
            log.error("Update failed",e);
            throw new RepositoryException("Update failed",e);
        }
    }

    @Override
    public void delete(T entity) {
        try{
            em.remove(entity);
        } catch (PersistenceException e){
            log.error("Delete failed",e);
            throw new RepositoryException("Delete failed",e);
        }
    }

    @Override
    public List<T> findAll() {
        try{
            //criteria query is needed
            CriteriaBuilder cb = em.getCriteriaBuilder ();
            CriteriaQuery<T> criteriaQuery = cb.createQuery (type);
            Root<T> rootEntry = criteriaQuery.from (type);
            CriteriaQuery<T> all = criteriaQuery.select (rootEntry);
            TypedQuery<T> allQuery = em.createQuery (all);
            return allQuery.getResultList ();

        } catch (PersistenceException e){
            log.error("FindAll failed",e);
            throw new RepositoryException("FindAll failed",e);
        }
    }
}
