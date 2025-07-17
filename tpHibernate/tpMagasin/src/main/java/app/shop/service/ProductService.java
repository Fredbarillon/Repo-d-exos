package app.shop.service;

import app.shop.DAO.BaseDAO;
import app.shop.DAO.ProductDAO;
import app.shop.entity.Product;

import java.util.List;

public class ProductService {

    private final BaseDAO<Product> baseDAO = new BaseDAO<>(Product.class) {};
    private final ProductDAO productDAO = new ProductDAO();

    public Product createOrUpdate(Product product) {
        return baseDAO.createOrUpdate(product);
    }

    public Product get(long id) {
        return baseDAO.get(id);
    }

    public List<Product> getAll() {
        return baseDAO.getAll();
    }

    public boolean delete(long id) {
        return baseDAO.delete(id);
    }

    public boolean restock(long id, int quantity) {
        return productDAO.restock(id, quantity);
    }
}
