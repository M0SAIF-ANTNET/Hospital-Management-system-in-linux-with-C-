package src.model;

import src.enums.MealCategory;

public class Meal {
    private final String id;
    private String name;
    private MealCategory category;
    private double price;
    private boolean available;

    public Meal(String id, String name, MealCategory category, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MealCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + category + " | $" + String.format("%.2f", price) + " | " + (available ? "Available" : "Out");
    }
}
