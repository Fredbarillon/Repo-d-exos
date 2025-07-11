package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

    private static final String URI = "jdbc:mysql://localhost:3306/gestionBancaire";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URI,USERNAME,PASSWORD);
    }
}
