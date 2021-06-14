package com.company.filter;

import com.company.model.Service;

import java.util.Comparator;

public class SortServicesByPrice implements Comparator<Service> {

    @Override
    public int compare(Service o1, Service o2) {
        return (int) (o1.getPrice() - o2.getPrice());
    }
}
