package src.model;

import src.enums.OfferType;

public class Offer {
    private final String id;
    private final String title;
    private final OfferType type;
    private final double value;
    private final double minimumOrderAmount;
    private boolean active;

    public Offer(String id, String title, OfferType type, double value, double minimumOrderAmount, boolean active) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.value = value;
        this.minimumOrderAmount = minimumOrderAmount;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public OfferType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public double getMinimumOrderAmount() {
        return minimumOrderAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
