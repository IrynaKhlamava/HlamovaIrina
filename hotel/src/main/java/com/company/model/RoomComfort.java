package com.company.model;

import com.company.exceptions.ServiceException;

public enum RoomComfort {
    THREE_STARS(0),
    FOUR_STARS(1),
    FIVE_STARS(2);

    private int value;

    RoomComfort(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static RoomComfort getRoomComfortByNum(Integer num) {
        if (num < 0 || num > 2) {
            throw new ServiceException(String.format("Введены некорректные данные"));
        }else {
            for (RoomComfort roomComfort : RoomComfort.values()) {
                if (num == roomComfort.getValue())
                    return roomComfort;
            }
            return null;
        }
    }

}

