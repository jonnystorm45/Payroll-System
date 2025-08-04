import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SecurityModule {
    private HasherStrategy hasher;

    public SecurityModule(HasherStrategy hasher) {
        this.hasher = hasher;
    }

   public boolean login(String username, String password) {
    try {
        System.out.println("DEBUG: Username entered: " + username);
        System.out.println("DEBUG: Password entered (raw): " + password);

        String hashedPassword = hasher.hash(password);
        System.out.println("DEBUG: Password hashed: " + hashedPassword);

        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
            System.out.println("DEBUG: Executing query...");
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("✅ Login successful!");
                return true;
            } else {
                System.out.println("DEBUG: No rows matched the query.");
                return false;
            }
        }
    } catch (Exception e) {
        System.err.println("Error during login: " + e.getMessage());
        return false;
    }
}



}
