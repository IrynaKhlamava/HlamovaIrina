package com.company.filter;

import com.company.model.Guest;

import java.util.Comparator;
import java.util.Date;


public class SortByDeparture implements Comparator<Guest> {

    @Override
    public int compare(Guest guest1, Guest guest2) {
        if (guest1.getDateCheckOut().isAfter(guest2.getDateCheckOut())) return 1;
        if (guest1.getDateCheckOut().isBefore(guest2.getDateCheckOut())) return -1;
        return 0;

    }
}
