package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class JdbcUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/pttk";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Mật khẩu trống


    // Load the JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Get a connection to the database
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu !", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }

    // Close the connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
