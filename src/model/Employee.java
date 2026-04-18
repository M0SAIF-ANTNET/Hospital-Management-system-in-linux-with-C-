package src.model;

import src.enums.Role;

public class Employee extends User {
    private String position;

    public Employee(String id, String name, String phone, String position) {
        super(id, name, phone, Role.EMPLOYEE);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
