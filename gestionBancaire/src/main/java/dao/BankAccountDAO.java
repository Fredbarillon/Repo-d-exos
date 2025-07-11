package dao;

import entity.BankAccount;
import util.DataBaseManager;

import java.sql.*;

public class BankAccountDAO {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String request;

    public BankAccount save(BankAccount account) {
        try {
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO bankaccount (customer_id, totalammount) VALUES (?, ?)";
            statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, account.getCustomerId());
            statement.setDouble(2, account.getTotalAmount());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
                System.out.println("Compte créé avec ID : " + account.getId());
            }
            return account;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du compte : " + e.getMessage());
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deposit(int accountId, double amount) {
        try {
            connection = DataBaseManager.getConnection();
            request = "UPDATE bankaccount SET totalamount = totalamount + ? WHERE id = ?";
            statement = connection.prepareStatement(request);
            statement.setDouble(1, amount);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors du dépôt : " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void withdraw(int accountId, double amount) {
        try {
            connection = DataBaseManager.getConnection();
            request = "UPDATE bankaccount SET totalamount = totalamount - ? WHERE id = ?";
            statement = connection.prepareStatement(request);
            statement.setDouble(1, amount);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors du retrait : " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public BankAccount findById(int id) {
        try {
            connection = DataBaseManager.getConnection();
            request = "SELECT * FROM bankaccount WHERE id = ?";
            statement = connection.prepareStatement(request);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return BankAccount.builder()
                        .id(resultSet.getInt("id"))
                        .customerId(resultSet.getInt("customer_id"))
                        .totalAmount(resultSet.getDouble("totalamount"))
                        .build();
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du compte : " + e.getMessage());
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
