package src.model;

import src.enums.Role;

public class Admin extends User {
    public Admin(String id, String name, String phone) {
        super(id, name, phone, Role.ADMIN);
    }
}
