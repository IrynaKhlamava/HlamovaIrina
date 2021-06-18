package com.company.filter;

import com.company.model.Service;

import java.util.Comparator;

public class SortServicesByName implements Comparator<Service> {

    @Override
    public int compare(Service service1, Service service2) {
        return service1.getName().compareTo(service2.getName());

    }
}
