package ru.kata.spring.boot_security.demo.model;

public enum Permissions {
    USER_READ("users:read"),
    USER_WRITE("users:write");

    private final String permisson;

    Permissions(String permisson) {
        this.permisson = permisson;
    }

    public String getPermisson() {
        return permisson;
    }
}
