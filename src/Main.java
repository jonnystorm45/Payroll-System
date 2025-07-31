import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pick hash algorithm:");
        System.out.println("1. SHA-256");
        System.out.println("2. MD5");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        HasherStrategy hasher = (choice == 2) ? new MD5Hasher() : new SHA256Hasher();
        new SecurityModule(hasher).registerUser();
    }
}