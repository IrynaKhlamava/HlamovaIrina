package com.company.filter;

import com.company.model.AdditionalServices;

import java.util.Comparator;

public class SortAddServicesByPrice implements Comparator<AdditionalServices> {

    @Override
    public int compare(AdditionalServices o1, AdditionalServices o2) {
        return (int) (o1.getPrice() - o2.getPrice());
    }
}
