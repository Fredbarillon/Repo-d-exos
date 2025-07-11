package service;

import dao.BankAccountDAO;
import dao.OperationDAO;
import entity.BankAccount;
import entity.Operation;
import util.OperationStatus;

public class BankAccountService {
    private final BankAccountDAO bankAccountDAO = new BankAccountDAO();
    private final OperationDAO operationDAO = new OperationDAO();

    public BankAccount create(int customerId) {
        BankAccount account = BankAccount.builder()
                .customerId(customerId)
                .totalAmount(0.0)
                .build();
        return bankAccountDAO.save(account);
    }

    public void deposit(int accountId, double amount) {
        bankAccountDAO.deposit(accountId, amount);
        Operation op = Operation.builder()
                .accountId(accountId)
                .amount(amount)
                .status(OperationStatus.DEPOSIT)
                .build();
        operationDAO.save(op);
    }

    public void withdraw(int accountId, double amount) {
        bankAccountDAO.withdraw(accountId, amount);
        Operation op = Operation.builder()
                .accountId(accountId)
                .amount(amount)
                .status(OperationStatus.WITHDRAWAL)
                .build();
        operationDAO.save(op);
    }

    public BankAccount findById(int id) {
        return bankAccountDAO.findById(id);
    }
}
