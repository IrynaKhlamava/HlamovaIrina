package com.company.util;

import com.company.model.AEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilteredListSorted<T extends AEntity> {
    public static<T extends AEntity> List<T> getFilteredListSorted(List<T> filteredList, Comparator<T> comparator) {
        return filteredList
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
