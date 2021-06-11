package com.company.util;

public class IdCreate {

    private static Long guestId = 1L;

    private static Long roomId = 1L;

    private static Long serviceId = 1L;

    public static Long createGuestId() {
        return guestId++;
    }

    public static Long createRoomId() {
        return roomId++;
    }

    public static Long createServiceId() {
        return serviceId++;
    }
}
