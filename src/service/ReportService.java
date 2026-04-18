package src.service;

public class ReportService {
    private final RestaurantDataStore store;

    public ReportService(RestaurantDataStore store) {
        this.store = store;
    }

    public String customerReport() {
        return "Customers: " + store.getCustomers().size();
    }

    public String employeeReport() {
        return "Employees: " + store.getEmployees().size();
    }

    public String salesReport() {
        double sales = store.getPayments().stream().mapToDouble(p -> p.getAmount()).sum();
        return "Total Sales: $" + String.format("%.2f", sales) + " | Payments: " + store.getPayments().size();
    }
}
