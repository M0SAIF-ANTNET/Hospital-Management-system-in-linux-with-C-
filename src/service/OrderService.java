package src.service;

import java.util.Optional;

import src.enums.OrderStatus;
import src.model.Customer;
import src.model.Employee;
import src.model.Meal;
import src.model.Order;
import src.model.OrderItem;
import src.util.IdGenerator;

public class OrderService {
    private final RestaurantDataStore store;

    public OrderService(RestaurantDataStore store) {
        this.store = store;
    }

    public Order createOrder(Customer customer, Employee employee) {
        Order order = new Order(IdGenerator.nextOrderId(), customer, employee);
        store.getOrders().add(order);
        return order;
    }

    public void addItem(Order order, Meal meal, int quantity) {
        if (!meal.isAvailable() || quantity <= 0) {
            throw new IllegalArgumentException("Meal unavailable or invalid quantity");
        }
        order.addItem(new OrderItem(meal, quantity));
        order.setStatus(OrderStatus.CONFIRMED);
    }

    public void cancelOrder(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
    }

    public Optional<Order> findById(String id) {
        return store.getOrders().stream().filter(o -> o.getId().equals(id)).findFirst();
    }
}
