package com.savaz.delivery.model.entity.enums;

public enum Status {
    OPENED("OPENED"),
    CONFIRMED("CONFIRMED"),
    PAID("PAID"),
    CLOSED("CLOSED");

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private final String name;
}
