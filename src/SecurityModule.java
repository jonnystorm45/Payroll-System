import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class SecurityModule {
    private final HasherStrategy hasher;
    public SecurityModule(HasherStrategy hasher) { this.hasher = hasher; }

    public void registerUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            String hashed = hasher.hash(password);

            Connection conn = DatabaseManager.getConnection();
            String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashed);
            stmt.executeUpdate();

            System.out.println("✅ User registered with hash: " + hashed);
        } catch (Exception e) {
            System.err.println("❌ Registration failed: " + e.getMessage());
        }
    }
}