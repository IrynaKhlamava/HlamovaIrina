package com.company.model;

import java.time.LocalDate;

public class RoomFilter {
    private LocalDate date;
    private String sortField;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
}
