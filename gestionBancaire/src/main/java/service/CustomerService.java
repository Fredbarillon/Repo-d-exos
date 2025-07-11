package service;

import dao.CustomerDAO;
import entity.Customer;

public class CustomerService {
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final BankAccountService bankAccountService = new BankAccountService();

    public Customer save(Customer customer) {
        Customer savedCustomer = customerDAO.save(customer);
        if (savedCustomer != null) {
            bankAccountService.create(savedCustomer.getId());
        }
        return savedCustomer;
    }
    
}
