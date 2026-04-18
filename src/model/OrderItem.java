package src.model;

public class OrderItem {
    private final Meal meal;
    private final int quantity;

    public OrderItem(Meal meal, int quantity) {
        this.meal = meal;
        this.quantity = quantity;
    }

    public Meal getMeal() {
        return meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubTotal() {
        return meal.getPrice() * quantity;
    }
}
