package src.service;

import java.util.Optional;

import src.model.Customer;
import src.model.Reward;
import src.util.IdGenerator;

public class RewardService {
    private final RestaurantDataStore store;

    public RewardService(RestaurantDataStore store) {
        this.store = store;
    }

    public Optional<Reward> evaluate(Customer customer) {
        if (customer.getTotalSpent() >= 200 && customer.getLoyaltyPoints() >= 20) {
            Reward reward = new Reward(IdGenerator.nextRewardId(), customer.getId(), "Free dessert coupon");
            store.getRewards().add(reward);
            return Optional.of(reward);
        }
        return Optional.empty();
    }
}
