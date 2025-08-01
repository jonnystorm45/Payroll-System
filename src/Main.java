import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        // Hash algorithm selection
        System.out.println("Pick hash algorithm:");
        System.out.println("1. SHA-256");
        System.out.println("2. MD5");

        int hashChoice = -1;
        while (hashChoice != 1 && hashChoice != 2) {
            System.out.print("Enter 1 or 2: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number (1 or 2): ");
                scanner.next(); // discard invalid
            }
            hashChoice = scanner.nextInt();
            scanner.nextLine(); // flush newline
        }

        HasherStrategy hasher = (hashChoice == 2) ? new MD5Hasher() : new SHA256Hasher();
        SecurityModule securityModule = new SecurityModule(hasher);
        securityModule.registerUser(); // Register user

        // No need for extra scanner.nextLine() here

        // Begin payroll menu loop
        while (true) {
            System.out.println("\n--- Payroll Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Calculate Payroll");
            System.out.println("3. View Employees");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number: ");
                scanner.next(); // discard invalid input
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // flush newline

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> calculatePayroll();
                case 3 -> viewEmployees();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void addEmployee() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter hours worked: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number for hours worked: ");
            scanner.next(); // discard invalid input
        }
        double hours = scanner.nextDouble();

        System.out.print("Enter hourly rate: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number for hourly rate: ");
            scanner.next(); // discard invalid input
        }
        double rate = scanner.nextDouble();

        scanner.nextLine(); // flush newline
        employees.add(new Employee(name, hours, rate));
        System.out.println("✅ Employee added.");
    }

    public static void calculatePayroll() {
        if (employees.isEmpty()) {
            System.out.println("⚠️ No employees to calculate payroll for.");
            return;
        }

        for (Employee e : employees) {
            double gross = PayrollCalculator.calculateGrossPay(e.hoursWorked, e.hourlyRate);
            double tax = gross * 0.20;
            double net = gross - tax;
            System.out.printf("Employee: %s | Gross: %.2f | Tax: %.2f | Net: %.2f\n", e.name, gross, tax, net);
        }
    }

    public static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("⚠️ No employees available.");
            return;
        }

        for (Employee e : employees) {
            System.out.printf("Name: %s | Hours: %.2f | Rate: %.2f\n", e.name, e.hoursWorked, e.hourlyRate);
        }
    }
}
