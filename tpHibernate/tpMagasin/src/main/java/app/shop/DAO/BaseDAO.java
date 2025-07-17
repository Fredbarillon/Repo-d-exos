package app.shop.DAO;

import app.shop.util.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class BaseDAO<T> {

    protected final SessionFactory sessionFactory;
    protected final Class<T> tClass;

    public BaseDAO(Class<T> tClass) {
        this.sessionFactory = SessionFactorySingleton.getSessionFactory();
        this.tClass = tClass;
    }

    public T createOrUpdate(T element) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(element);
            transaction.commit();
            return element;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            return null;
        }
    }

    public T get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(tClass, id);
        }
    }

    public boolean delete(long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            T entity = session.get(tClass, id);
            if (entity != null) {
                tx = session.beginTransaction();
                session.delete(entity);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + tClass.getSimpleName(), tClass).list();
        }
    }
}
