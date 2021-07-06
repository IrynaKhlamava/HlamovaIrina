package com.company.model;

import java.io.Serializable;
import java.util.Objects;

public class Service extends AEntity  implements Serializable {

    private String name;
    private double price;


    public Service(String name, double price) {
        this.name = name;
        this.price = price;
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
