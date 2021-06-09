package com.company.model;

public enum AdditionalServices {
    BEAUTY_SALON(35.00, "Beauty salon"),
    GYM(20, "Gym"),
    POOL(15, "Pool"),
    RENT_HALL(40, "Rent hall");

    private final double price;
    private final String name;

    AdditionalServices(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
