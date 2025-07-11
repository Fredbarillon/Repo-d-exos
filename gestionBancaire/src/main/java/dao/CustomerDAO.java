package dao;

import entity.Customer;
import util.DataBaseManager;

import java.sql.*;

public class CustomerDAO {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String request;

    public Customer save(Customer customer) {
        try {
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO customer (firstname, lastname, phone) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getFirstname());
            statement.setString(2, customer.getLastname());
            statement.setString(3, customer.getPhone());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    customer.setId(resultSet.getInt(1));
                    System.out.println("Client créé avec ID : " + customer.getId());
                }
            }
            return customer;

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement du client : " + e.getMessage());
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

    public Customer findById(int id) {
        try {
            connection = DataBaseManager.getConnection();
            request = "SELECT * FROM customer WHERE id = ?";
            statement = connection.prepareStatement(request);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Customer.builder()
                        .id(resultSet.getInt("id"))
                        .firstname(resultSet.getString("firstname"))
                        .lastname(resultSet.getString("lastname"))
                        .phone(resultSet.getString("phone"))
                        .build();
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du client : " + e.getMessage());
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
