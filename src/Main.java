import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame {
    private HasherStrategy hasher;
    private SecurityModule securityModule;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JPanel loginPanel, mainPanel;
    private DefaultListModel<String> employeeListModel;

    public Main() {
        super("Payroll System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setupLogin();
    }

    private void setupLogin() {
    loginPanel = new JPanel();
    loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
    add(loginPanel);

    loginPanel.add(new JLabel("Select Hash Algorithm:"));
    String[] hashOptions = {"SHA-256", "MD5"};
    JComboBox<String> hashCombo = new JComboBox<>(hashOptions);
    loginPanel.add(hashCombo);

    loginPanel.add(new JLabel("Username:"));
    txtUsername = new JTextField();
    loginPanel.add(txtUsername);

    loginPanel.add(new JLabel("Password:"));
    txtPassword = new JPasswordField();
    loginPanel.add(txtPassword);

    btnLogin = new JButton("Login");
    loginPanel.add(btnLogin);

    btnLogin.addActionListener(e -> {
        int choice = hashCombo.getSelectedIndex();
        hasher = (choice == 1) ? new MD5Hasher() : new SHA256Hasher();
        securityModule = new SecurityModule(hasher);

        boolean success = securityModule.login(txtUsername.getText(), new String(txtPassword.getPassword()));
        if (success) {
            getContentPane().removeAll();
            setupMainUI();
            revalidate();
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Try again.");
        }
    });
}


    private void setupMainUI() {
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        employeeListModel = new DefaultListModel<>();
        JList<String> employeeList = new JList<>(employeeListModel);
        mainPanel.add(new JScrollPane(employeeList), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JTextField nameField = new JTextField(10);
        JTextField hoursField = new JTextField(5);
        JTextField rateField = new JTextField(5);
        JButton addButton = new JButton("Add Employee");
        JButton calculateButton = new JButton("Calculate Payroll");

        controlPanel.add(new JLabel("Name:"));
        controlPanel.add(nameField);
        controlPanel.add(new JLabel("Hours:"));
        controlPanel.add(hoursField);
        controlPanel.add(new JLabel("Rate:"));
        controlPanel.add(rateField);
        controlPanel.add(addButton);
        controlPanel.add(calculateButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double hours = Double.parseDouble(hoursField.getText());
                double rate = Double.parseDouble(rateField.getText());
                EmployeeDAO.addEmployee(name, hours, rate);
                refreshEmployeeList();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        calculateButton.addActionListener(e -> {
            try {
                List<Employee> employees = EmployeeDAO.getAllEmployees();
                StringBuilder report = new StringBuilder();
                for (Employee emp : employees) {
                    double gross = PayrollCalculator.calculateGrossPay(emp.getHoursWorked(), emp.getHourlyRate());
                    double tax = PayrollCalculator.calculateTax(gross);
                    double net = PayrollCalculator.calculateNetPay(gross, tax);
                    report.append(String.format("%s: Gross=%.2f, Tax=%.2f, Net=%.2f%n",
                        emp.getName(), gross, tax, net));
                }
                JOptionPane.showMessageDialog(this, report.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error calculating payroll: " + ex.getMessage());
            }
        });

        refreshEmployeeList();
    }

    private void refreshEmployeeList() {
        try {
            employeeListModel.clear();
            for (Employee e : EmployeeDAO.getAllEmployees()) {
                employeeListModel.addElement(String.format("%s - Hours: %.2f Rate: %.2f", e.getName(), e.getHoursWorked(), e.getHourlyRate()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading employees: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
