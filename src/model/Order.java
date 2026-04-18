package src.model;

import java.util.ArrayList;
import java.util.List;

import src.enums.OrderStatus;

public class Order {
    private final String id;
    private final Customer customer;
    private final Employee createdBy;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.CREATED;

    public Order(String id, Customer customer, Employee createdBy) {
        this.id = id;
        this.customer = customer;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getSubTotal).sum();
    }
}
