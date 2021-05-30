package com.savaz.delivery.model.entity.enums;

public enum City {
    KIEV("Kyiv"),
    KHARKIV("Kharkiv"),
    ODESA("Odesa"),
    DONETSK("Donetsk"),
    DNIPRO("Dnipro"),
    ZAPORIZHIA("Zaporizhia"),
    LVIV("Lviv");

    public String getName() {
        return name;
    }

    City(String name) {
        this.name = name;
    }

    private final String name;


}

