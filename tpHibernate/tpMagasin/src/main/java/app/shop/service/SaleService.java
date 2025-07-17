package app.shop.service;

import app.shop.DAO.BaseDAO;
import app.shop.DAO.SaleDAO;
import app.shop.entity.Sale;

import java.time.LocalDate;
import java.util.List;

public class SaleService {

    private final BaseDAO<Sale> baseDAO = new BaseDAO<>(Sale.class) {};
    private final SaleDAO saleDAO = new SaleDAO();

    public Sale createOrUpdate(Sale sale) {
        return baseDAO.createOrUpdate(sale);
    }

    public Sale get(long id) {
        return baseDAO.get(id);
    }

    public List<Sale> getAll() {
        return baseDAO.getAll();
    }

    public boolean delete(long id) {
        return baseDAO.delete(id);
    }

    public List<Sale> getByCustomer(long customerId) {
        return saleDAO.getByCustomerId(customerId);
    }

    public List<Sale> getByProduct(long productId) {
        return saleDAO.getByProductId(productId);
    }

    public List<Sale> getByPeriod(LocalDate from, LocalDate to) {
        return saleDAO.getByPeriod(from, to);
    }
}
