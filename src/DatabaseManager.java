import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC driver not found.", e);
            }
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PayrollDB?useSSL=false&serverTimezone=UTC",
                "root", "Jsa45582007@@"
            );
        }
        return conn;
    }
}
