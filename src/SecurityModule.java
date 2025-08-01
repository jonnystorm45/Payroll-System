import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class SecurityModule {
    private final HasherStrategy hasher;

    public SecurityModule(HasherStrategy hasher) {
        this.hasher = hasher;
    }

    public boolean registerUser() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String hashedPassword = this.hasher.hash(password);

            Connection conn = DatabaseManager.getConnection();
            String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();

            System.out.println("✅ User registered with hash: " + hashedPassword);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Registration failed: " + e.getMessage());
            return false;
        }
    }
}
