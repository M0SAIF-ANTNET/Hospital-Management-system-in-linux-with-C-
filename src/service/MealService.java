package src.service;

import java.util.List;
import java.util.Optional;

import src.enums.MealCategory;
import src.model.Meal;
import src.util.IdGenerator;
import src.util.InputValidator;

public class MealService {
    private final RestaurantDataStore store;

    public MealService(RestaurantDataStore store) {
        this.store = store;
    }

    public Meal add(String name, MealCategory category, double price) {
        InputValidator.requireNonBlank(name, "Meal name");
        InputValidator.requirePositive(price, "Meal price");
        Meal meal = new Meal(IdGenerator.nextMealId(), name, category, price, true);
        store.getMeals().add(meal);
        return meal;
    }

    public List<Meal> list() {
        return store.getMeals();
    }

    public Optional<Meal> findById(String id) {
        return store.getMeals().stream().filter(m -> m.getId().equals(id)).findFirst();
    }
}
