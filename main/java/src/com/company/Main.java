package com.company;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomDao;
import com.company.api.dao.IServiceDao;
import com.company.api.service.IGuestService;
import com.company.api.service.IRoomService;
import com.company.api.service.IServiceService;
import com.company.dao.GuestDao;
import com.company.dao.RoomDao;
import com.company.dao.ServiceDao;
import com.company.injection.ApplicationContext;
import com.company.injection.HotelApplication;
import com.company.model.*;
import com.company.service.GuestService;
import com.company.service.RoomService;
import com.company.service.ServiceService;
import com.company.ui.UiApplication;

public class Main {
    private static final IGuestDao guestDao = new GuestDao();

    private static final IGuestService guestService = new GuestService(guestDao);

    private static final IRoomDao roomDao = new RoomDao();

    private static final IRoomService roomService = new RoomService(roomDao, guestDao);

    private static final IServiceDao serviceDao = new ServiceDao();

    private static final IServiceService serviceService = new ServiceService(serviceDao);

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        HotelApplication.run(context);
//        UiApplication.run(context);
        // Guest guest1 = guestService.addGuest("Petr", 12);
        //guestDao.update(guest1);
//        System.out.println(guest1);
        //System.out.println(guestDao.getById(1L));
        //  Service service1 = serviceService.addService("newService",50.00, guest1);
        // System.out.println(service1);
        // System.out.println(guest1.getId());
//        System.out.println(guestDao.getAll());
//        System.out.println(roomDao.getAll());
        //System.out.println(serviceDao.getAll());
//        System.out.println("TotalNumber guests="+guestDao.getTotalNumber());
//        System.out.println("sortGuestsByName()="+guestService.sortGuestsByName());
        //Guest guest5 = guestDao.getById(15L);
        // guestDao.delete(guest5);
        // System.out.println(guestDao.getAll());
        // Room room1 = roomService.addRoom(15,2, 0, 47.00, 4);
        // System.out.println(roomService.getAll());
        //  roomService.checkIn(guestDao.getById(1L), roomDao.getById(1L));
        roomService.checkIn(guestDao.getById(2L), roomDao.getById(5L));
    }


}