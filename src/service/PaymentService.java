package src.service;

import src.enums.OrderStatus;
import src.enums.PaymentMethod;
import src.model.Bill;
import src.model.Customer;
import src.model.Order;
import src.model.Payment;
import src.util.IdGenerator;

public class PaymentService {
    private final RestaurantDataStore store;

    public PaymentService(RestaurantDataStore store) {
        this.store = store;
    }

    public Payment processPayment(Order order, Bill bill, PaymentMethod method) {
        Payment payment = new Payment(IdGenerator.nextPaymentId(), order.getId(), bill.getTotal(), method);
        order.setStatus(OrderStatus.PAID);
        Customer customer = order.getCustomer();
        customer.addSpent(bill.getTotal());
        customer.addLoyaltyPoints((int) bill.getTotal() / 10);
        customer.getOrderHistory().add(order.getId());
        store.getPayments().add(payment);
        return payment;
    }
}
