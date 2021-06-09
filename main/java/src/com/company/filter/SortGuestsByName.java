package com.company.filter;

import com.company.model.Guest;

import java.util.Comparator;

public class SortGuestsByName implements Comparator<Guest> {

    @Override
    public int compare(Guest guest1, Guest guest2) {
        return guest1.getName().compareTo(guest2.getName());

    }
}
