package src.service;

import java.util.List;
import java.util.Optional;

import src.model.Employee;
import src.util.IdGenerator;
import src.util.InputValidator;

public class EmployeeService {
    private final RestaurantDataStore store;

    public EmployeeService(RestaurantDataStore store) {
        this.store = store;
    }

    public Employee add(String name, String phone, String position) {
        InputValidator.requireNonBlank(name, "Employee name");
        Employee employee = new Employee(IdGenerator.nextUserId(), name, phone, position);
        store.getEmployees().add(employee);
        return employee;
    }

    public List<Employee> list() {
        return store.getEmployees();
    }

    public Optional<Employee> findById(String id) {
        return store.getEmployees().stream().filter(e -> e.getId().equals(id)).findFirst();
    }
}
