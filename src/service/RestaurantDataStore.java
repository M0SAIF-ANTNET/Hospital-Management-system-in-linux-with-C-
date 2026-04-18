package src.service;

import java.util.ArrayList;
import java.util.List;

import src.model.Customer;
import src.model.Employee;
import src.model.Meal;
import src.model.Notification;
import src.model.Offer;
import src.model.Order;
import src.model.Payment;
import src.model.Reward;

public class RestaurantDataStore {
    private final List<Employee> employees = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Meal> meals = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<Payment> payments = new ArrayList<>();
    private final List<Offer> offers = new ArrayList<>();
    private final List<Reward> rewards = new ArrayList<>();
    private final List<Notification> notifications = new ArrayList<>();

    public List<Employee> getEmployees() { return employees; }
    public List<Customer> getCustomers() { return customers; }
    public List<Meal> getMeals() { return meals; }
    public List<Order> getOrders() { return orders; }
    public List<Payment> getPayments() { return payments; }
    public List<Offer> getOffers() { return offers; }
    public List<Reward> getRewards() { return rewards; }
    public List<Notification> getNotifications() { return notifications; }
}
