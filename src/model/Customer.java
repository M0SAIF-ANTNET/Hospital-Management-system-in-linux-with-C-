package src.model;

import java.util.ArrayList;
import java.util.List;

import src.enums.Role;

public class Customer extends User {
    private final List<String> orderHistory = new ArrayList<>();
    private double totalSpent;
    private int loyaltyPoints;

    public Customer(String id, String name, String phone) {
        super(id, name, phone, Role.CUSTOMER);
    }

    public List<String> getOrderHistory() {
        return orderHistory;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void addSpent(double value) {
        totalSpent += value;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        loyaltyPoints += points;
    }
}
