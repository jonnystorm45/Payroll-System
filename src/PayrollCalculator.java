public class PayrollCalculator {
    public static double calculateGrossPay(double hoursWorked, double hourlyRate) {
        return hoursWorked * hourlyRate;
    }

    public static double calculateTax(double grossPay) {
        return grossPay * 0.20; // Flat 20% tax
    }

    public static double calculateNetPay(double grossPay, double tax) {
        return grossPay - tax;
    }
}
