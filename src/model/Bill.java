package src.model;

public class Bill {
    private final String orderId;
    private final double subTotal;
    private final double discount;
    private final double total;

    public Bill(String orderId, double subTotal, double discount, double total) {
        this.orderId = orderId;
        this.subTotal = subTotal;
        this.discount = discount;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }
}
