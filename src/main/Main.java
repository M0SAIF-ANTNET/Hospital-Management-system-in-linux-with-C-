package src.main;

import java.util.List;
import java.util.Scanner;

import src.enums.MealCategory;
import src.enums.NotificationType;
import src.enums.OfferType;
import src.enums.PaymentMethod;
import src.model.Bill;
import src.model.Customer;
import src.model.Employee;
import src.model.Meal;
import src.model.Order;
import src.service.BillingService;
import src.service.CustomerService;
import src.service.EmployeeService;
import src.service.MealService;
import src.service.NotificationService;
import src.service.OfferService;
import src.service.OrderService;
import src.service.PaymentService;
import src.service.ReportService;
import src.service.RestaurantDataStore;
import src.service.RewardService;

public class Main {
    public static void main(String[] args) {
        RestaurantDataStore store = new RestaurantDataStore();
        EmployeeService employeeService = new EmployeeService(store);
        CustomerService customerService = new CustomerService(store);
        MealService mealService = new MealService(store);
        OfferService offerService = new OfferService(store);
        BillingService billingService = new BillingService(offerService);
        OrderService orderService = new OrderService(store);
        PaymentService paymentService = new PaymentService(store);
        RewardService rewardService = new RewardService(store);
        NotificationService notificationService = new NotificationService(store);
        ReportService reportService = new ReportService(store);

        seedData(employeeService, customerService, mealService, offerService);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    employeeService.list().forEach(e -> System.out.println(e.getId() + " | " + e.getName() + " | " + e.getPosition()));
                    break;
                case 2:
                    customerService.list().forEach(c -> System.out.println(c.getId() + " | " + c.getName() + " | spent=$" + c.getTotalSpent()));
                    break;
                case 3:
                    mealService.list().forEach(System.out::println);
                    break;
                case 4:
                    createOrderFlow(scanner, employeeService, customerService, mealService, orderService, billingService,
                            paymentService, rewardService, notificationService);
                    break;
                case 5:
                    offerService.list().forEach(o -> System.out.println(o.getId() + " | " + o.getTitle()));
                    break;
                case 6:
                    System.out.println(reportService.customerReport());
                    System.out.println(reportService.employeeReport());
                    System.out.println(reportService.salesReport());
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }

    private static void createOrderFlow(
            Scanner scanner,
            EmployeeService employeeService,
            CustomerService customerService,
            MealService mealService,
            OrderService orderService,
            BillingService billingService,
            PaymentService paymentService,
            RewardService rewardService,
            NotificationService notificationService) {

        List<Employee> employees = employeeService.list();
        List<Customer> customers = customerService.list();
        List<Meal> meals = mealService.list();

        if (employees.isEmpty() || customers.isEmpty() || meals.isEmpty()) {
            System.out.println("Seed data missing");
            return;
        }

        Employee employee = employees.get(0);
        Customer customer = customers.get(0);

        Order order = orderService.createOrder(customer, employee);
        orderService.addItem(order, meals.get(0), 2);
        if (meals.size() > 1) {
            orderService.addItem(order, meals.get(1), 1);
        }

        Bill bill = billingService.generateBill(order);
        paymentService.processPayment(order, bill, PaymentMethod.CARD);

        notificationService.send(customer.getId(), NotificationType.BILLING,
                "Order " + order.getId() + " paid. Amount: $" + String.format("%.2f", bill.getTotal()));

        rewardService.evaluate(customer).ifPresent(reward -> {
            notificationService.send(customer.getId(), NotificationType.REWARD, "Reward unlocked: " + reward.getDescription());
            System.out.println("Reward: " + reward.getDescription());
        });

        System.out.println("Order created: " + order.getId());
        System.out.println("Subtotal: $" + String.format("%.2f", bill.getSubTotal()));
        System.out.println("Discount: $" + String.format("%.2f", bill.getDiscount()));
        System.out.println("Total: $" + String.format("%.2f", bill.getTotal()));

        System.out.println("Customer notifications:");
        notificationService.getByTarget(customer.getId()).forEach(System.out::println);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private static void seedData(
            EmployeeService employeeService,
            CustomerService customerService,
            MealService mealService,
            OfferService offerService) {
        employeeService.add("Ali", "01000000001", "Cashier");
        customerService.add("Sara", "01000000002");
        mealService.add("Margherita Pizza", MealCategory.MAIN_COURSE, 12.99);
        mealService.add("Lemonade", MealCategory.DRINK, 3.50);
        offerService.createOffer("10% Off over $15", OfferType.PERCENTAGE, 10, 15);
    }

    private static void printMenu() {
        System.out.println("\n=== Restaurant Management System ===");
        System.out.println("1. List employees");
        System.out.println("2. List customers");
        System.out.println("3. List meals");
        System.out.println("4. Create sample order + bill + payment");
        System.out.println("5. List offers");
        System.out.println("6. View reports");
        System.out.println("0. Exit");
        System.out.print("Select: ");
    }
}
