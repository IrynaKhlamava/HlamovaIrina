package com.company.model;

import com.company.exceptions.ServiceException;

public enum RoomComfort {
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);

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
        if (num < 3 || num > 5) {
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

