package src.model;

import src.enums.Role;

public abstract class User extends Person {
    private final Role role;

    protected User(String id, String name, String phone, Role role) {
        super(id, name, phone);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
