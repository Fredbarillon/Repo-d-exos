package dao;

import entity.Operation;
import util.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperationDAO {
    private Connection connection;
    private PreparedStatement statement;
    private String request;

    public void save(Operation operation) {
        try {
            connection = DataBaseManager.getConnection();
            request = "INSERT INTO operation (account_id, amount, status) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(request);
            statement.setInt(1, operation.getAccountId());
            statement.setDouble(2, operation.getAmount());
            statement.setString(3, operation.getStatus().toString());

            statement.executeUpdate();
            System.out.println("Opération enregistrée : " + operation.getStatus());

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement de l'opération : " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
