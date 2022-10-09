package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final Connection connection;

static {
    final String URL = "jdbc:MySQL://localhost:3306/database";
    final String USER = "root";
    final String PASSWORD = "root1";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException | SQLException e) {
        throw new RuntimeException("trouble while getting connection");
    }
}

    public static Connection getConnection() {
        return connection;
    }

}
