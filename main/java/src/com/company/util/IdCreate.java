package com.company.util;

public class IdCreate {

    private static Long guestId = 1L;

    private static Long roomId = 1L;

    private static Long serviceId = 1L;

    public static Long createGuestId() {
        return guestId++;
    }

    public static void setGuestId(Long guestId) {
        IdCreate.guestId = guestId;
    }

    public static void setRoomId(Long roomId) {
        IdCreate.roomId = roomId;
    }

    public static void setServiceId(Long serviceId) {
        IdCreate.serviceId = serviceId;
    }

    public static Long createRoomId() {
        return roomId++;
    }

    public static Long createServiceId() {
        return serviceId++;
    }
}
