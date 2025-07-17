package app.shop.service;

import app.shop.DAO.BaseDAO;
import app.shop.entity.Customer;

import java.util.List;

public class CustomerService {

    private final BaseDAO<Customer> baseDAO = new BaseDAO<>(Customer.class) {};

    public Customer createOrUpdate(Customer customer) {
        return baseDAO.createOrUpdate(customer);
    }

    public Customer get(long id) {
        return baseDAO.get(id);
    }

    public List<Customer> getAll() {
        return baseDAO.getAll();
    }

    public boolean delete(long id) {
        return baseDAO.delete(id);
    }
}
