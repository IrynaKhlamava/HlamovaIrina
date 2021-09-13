package com.company.model;

import com.company.exceptions.ServiceException;

public enum RoomStatus {
    CLEAN,
    OUT_OF_ORDER,
    DIRTY,
    OCCUPY,
    DEPARTURE;

    public static RoomStatus getRoomStatusByNum(Integer num) {
        if (num > RoomStatus.values().length || num < 0) {
            throw new ServiceException(String.format("Введены некорректные данные"));
        } else {
            for (RoomStatus roomStatus : RoomStatus.values()) {
                if (num == roomStatus.ordinal()) {
                    return roomStatus;
                }
            }
            return null;
        }
    }
}
