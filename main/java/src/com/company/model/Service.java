package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public class Service extends AEntity {

    private String name;
    private double price;
    private Long guestId;

    public Service() {
    }

    public Service(String name, double price, Guest guest) {
        this.name = name;
        this.price = price;
        this.guestId = guest.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Double.compare(service.price, price) == 0 &&
                Objects.equals(name, service.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
