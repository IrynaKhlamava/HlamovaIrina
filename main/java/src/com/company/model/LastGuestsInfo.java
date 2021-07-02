package com.company.model;

import java.time.LocalDate;
import java.util.Objects;

public class LastGuestsInfo {

    private String name;
    private LocalDate dateCheckIn;
    private LocalDate dateCheckOut;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(LocalDate dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastGuestsInfo)) return false;
        LastGuestsInfo that = (LastGuestsInfo) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(dateCheckIn, that.dateCheckIn) &&
                Objects.equals(dateCheckOut, that.dateCheckOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateCheckIn, dateCheckOut);
    }

    public LocalDate getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(LocalDate dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    @Override
    public String toString() {
        return "LastGuestsInfo{" +
                "name='" + name + '\'' +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                '}';
    }
}
