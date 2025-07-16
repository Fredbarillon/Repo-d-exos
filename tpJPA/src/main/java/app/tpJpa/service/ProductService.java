package app.tpJpa.service;

import app.tpJpa.dao.BaseDAO;
import app.tpJpa.entity.Product;

import java.util.List;

public class ProductService {

    private final BaseDAO<Product> productDAO;

    public ProductService() {
        this.productDAO = new BaseDAO<>(Product.class) {
            @Override
            public List<Product> get() {
                return em.createQuery("FROM Product", Product.class).getResultList();
            }

            @Override
            public Product update(Product element, long id) {
                em.getTransaction().begin();
                Product updated = em.merge(element);
                em.getTransaction().commit();
                return updated;
            }
        };
    }

    public Product save(Product product) {
        return productDAO.save(product);
    }

    public Product get(long id) {
        return productDAO.get(id);
    }

    public boolean delete(long id) {
        return productDAO.delete(id);
    }

    public List<Product> getAll() {
        return productDAO.get();
    }

    public Product update(Product product, long id) {
        return productDAO.update(product, id);
    }
}
