package app.tpJpa.dao;

import app.tpJpa.util.DatabaseManager;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class BaseDAO<T> {

    protected final EntityManager em;
    protected final Class<T> tClass;

    public BaseDAO(Class<T> tClass) {
        this.em = DatabaseManager.getEntityManager();
        this.tClass = tClass;
    }

    public T save(T element) {
        em.getTransaction().begin();
        em.persist(element);
        em.getTransaction().commit();
        return element;
    }

    public T get(long id) {
        return em.find(tClass, id);
    }

    public boolean delete(long id) {
        T elementFound = get(id);
        if (elementFound != null) {
            em.getTransaction().begin();
            em.remove(elementFound);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    public abstract List<T> get();

    public abstract T update(T element, long id);
}
