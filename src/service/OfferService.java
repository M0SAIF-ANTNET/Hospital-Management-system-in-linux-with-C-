package src.service;

import java.util.List;

import src.enums.OfferType;
import src.model.Offer;
import src.util.IdGenerator;

public class OfferService {
    private final RestaurantDataStore store;

    public OfferService(RestaurantDataStore store) {
        this.store = store;
    }

    public Offer createOffer(String title, OfferType type, double value, double minOrder) {
        Offer offer = new Offer(IdGenerator.nextOfferId(), title, type, value, minOrder, true);
        store.getOffers().add(offer);
        return offer;
    }

    public double calculateDiscount(double amount) {
        double best = 0;
        for (Offer offer : store.getOffers()) {
            if (!offer.isActive() || amount < offer.getMinimumOrderAmount()) {
                continue;
            }
            double discount = offer.getType() == OfferType.FLAT
                    ? offer.getValue()
                    : (amount * offer.getValue() / 100.0);
            best = Math.max(best, discount);
        }
        return Math.min(best, amount);
    }

    public List<Offer> list() {
        return store.getOffers();
    }
}
