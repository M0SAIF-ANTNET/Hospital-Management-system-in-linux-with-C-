package src.model;

public class Reward {
    private final String id;
    private final String customerId;
    private final String description;

    public Reward(String id, String customerId, String description) {
        this.id = id;
        this.customerId = customerId;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getDescription() {
        return description;
    }
}
