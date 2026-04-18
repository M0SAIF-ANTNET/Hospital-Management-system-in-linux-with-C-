package src.model;

import src.enums.PaymentMethod;

public class Payment {
    private final String id;
    private final String orderId;
    private final double amount;
    private final PaymentMethod method;

    public Payment(String id, String orderId, double amount, PaymentMethod method) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }
}
