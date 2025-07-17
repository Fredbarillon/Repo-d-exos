package app.shop.DAO;

import app.shop.entity.Sale;
import app.shop.util.SessionFactorySingleton;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class SaleDAO {

    private final Session session = SessionFactorySingleton.getSessionFactory().openSession();

    public List<Sale> getByCustomerId(long customerId) {
        return session.createQuery("FROM Sale s WHERE s.customer.id = :customerId", Sale.class).setParameter("customerId", customerId).list();
    }

    public List<Sale> getByProductId(long productId) {
        return session.createQuery("SELECT s FROM Sale s JOIN s.productsSold p WHERE p.id = :productId", Sale.class).setParameter("productId", productId).list();
    }

    public List<Sale> getByPeriod(LocalDate from, LocalDate to) {
        return session.createQuery("FROM Sale s WHERE s.date BETWEEN :from AND :to", Sale.class).setParameter("from", from).setParameter("to", to).list();
    }
}
