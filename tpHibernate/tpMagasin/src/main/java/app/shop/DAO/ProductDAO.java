package app.shop.DAO;

import app.shop.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDAO extends BaseDAO<Product> {

    public ProductDAO() {
        super(Product.class);
    }

    public boolean restock(long id, int quantityToAdd) {
        Product product = get(id);
        if (product == null) return false;

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            int currentStock = product.getStock();
            product.setStock(currentStock + quantityToAdd);
            session.update(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
