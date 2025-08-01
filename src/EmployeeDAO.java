import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public static List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("hours_worked"),
                    rs.getDouble("hourly_rate")
                ));
            }
        }
        return employees;
    }

    public static void addEmployee(String name, double hours, double rate) throws SQLException {
        try (Connection conn = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO employees (name, hours_worked, hourly_rate) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, hours);
            stmt.setDouble(3, rate);
            stmt.executeUpdate();
        }
    }
}
