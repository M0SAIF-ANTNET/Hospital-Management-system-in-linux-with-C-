package src.service;

import src.model.Bill;
import src.model.Order;

public class BillingService {
    private final OfferService offerService;

    public BillingService(OfferService offerService) {
        this.offerService = offerService;
    }

    public Bill generateBill(Order order) {
        double subTotal = order.getTotal();
        double discount = offerService.calculateDiscount(subTotal);
        double total = subTotal - discount;
        return new Bill(order.getId(), subTotal, discount, total);
    }
}
