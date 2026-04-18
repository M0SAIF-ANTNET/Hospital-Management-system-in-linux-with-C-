package src.service;

import java.util.List;
import java.util.Optional;

import src.model.Customer;
import src.util.IdGenerator;
import src.util.InputValidator;

public class CustomerService {
    private final RestaurantDataStore store;

    public CustomerService(RestaurantDataStore store) {
        this.store = store;
    }

    public Customer add(String name, String phone) {
        InputValidator.requireNonBlank(name, "Customer name");
        Customer customer = new Customer(IdGenerator.nextUserId(), name, phone);
        store.getCustomers().add(customer);
        return customer;
    }

    public List<Customer> list() {
        return store.getCustomers();
    }

    public Optional<Customer> findById(String id) {
        return store.getCustomers().stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
