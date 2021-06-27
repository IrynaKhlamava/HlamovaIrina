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
import com.company.model.*;
import com.company.service.GuestService;
import com.company.service.RoomService;
import com.company.service.ServiceService;

import java.time.LocalDate;

public class Hotel {

    private static final IGuestDao guestDao = new GuestDao();

    private static final IGuestService guestService = new GuestService(guestDao);

    private static final IRoomDao roomDao = new RoomDao();

    private static final IRoomService roomService = new RoomService(roomDao);

    private static final IServiceDao serviceDao = new ServiceDao();

    private static final IServiceService serviceService = new ServiceService(serviceDao);

    public static void main(String[] args) {
        // write your code here
        Guest guest = guestService.addGuest("Guest1", 5);
        // System.out.println(guest);
        Guest guest2 = guestService.addGuest("Guest4", 1);
        Guest guest3 = guestService.addGuest("Guest2", 2);
        System.out.println("All guests " + guestDao.getAll());
        Guest guest11 = guestService.addGuest("Guest11", 11);
        Guest guest12 = guestService.addGuest("Guest12", 8);
        Guest guest13 = guestService.addGuest("Guest13", 4);
        Guest guest14 = guestService.addGuest("Guest14", 7);
        System.out.println();
        System.out.println("Get guest by Id = 3L: " + guestDao.getById(3L));
        System.out.println();

        Guest guest4 = guestService.addGuest("Guest3", 4);

        Room room = roomService.addRoom(1, 4, RoomStatus.CLEAN, 50.00, RoomComfort.FOUR_STARS);

        Room room2 = roomService.addRoom(2, 2, RoomStatus.CLEAN, 75.00, RoomComfort.FIVE_STARS);

        Room room3 = roomService.addRoom(3, 1, RoomStatus.CLEAN, 105.30, RoomComfort.THREE_STARS);

        Room room4 = roomService.addRoom(4, 2, RoomStatus.CLEAN, 125.00, RoomComfort.FOUR_STARS);

        Room room5 = roomService.addRoom(5, 1, RoomStatus.CLEAN, 180.00, RoomComfort.FIVE_STARS);

        //Поселить в номер
        roomService.checkIn(guest, room);
        roomService.checkIn(guest2, room);
        roomService.checkIn(guest11, room);
        roomService.checkIn(guest3, room2);
        roomService.checkIn(guest4, room3);
        System.out.println("Room number 1 " + roomService.getByRoomNumber(1));

        //Выселить из номера
        roomService.checkOut(guest2, room);
        System.out.println("Room number 1 after checkOut guest2");
        System.out.println(roomService.getByRoomNumber(1));
        roomService.checkIn(guest2, room3);
        roomService.checkIn(guest2, room2);
        roomService.checkIn(guest12, room);
        roomService.checkIn(guest13, room);

        System.out.println("Guests by room number 2 " + roomService.getByRoomNumber(2));

        //Изменить статус номера на ремонтируемый/обслуживаемый
        roomService.changeStatusByRoomNumber(1, RoomStatus.OUT_OF_ORDER);
        System.out.println("Changed room status ");

        roomService.changePrice(1, 66.50);
        //Изменить цену номера или услуги
        System.out.println("Changed price room number 1 ");
        System.out.println(roomService.getByRoomNumber(1));

        System.out.println();

        //Добавить услугу
        System.out.println(serviceService.addService("Beauty salon", 35, guest));
        System.out.println(serviceService.addService("Gym", 20, guest));
        System.out.println(serviceService.addService("Pool", 15, guest));
        System.out.println(serviceService.addService("Rent hall", 40, guest));
        System.out.println(serviceService.addService("Rent hall", 40, guest2));

        //Список номеров (сортировать по вместимости)
        System.out.println("sort by Capacity");
        System.out.println(roomService.sortRoomByCapacity());

        //Список номеров (сортировать по цене)
        System.out.println("sort by Price");
        System.out.println(roomService.sortRoomByPrice());

        //Список номеров (сортировать по количеству звезд)
        System.out.println("sort by Comfort");
        System.out.println(roomService.sortRoomByComfort());

        //Список свободных номеров
        System.out.println("all free room");
        //System.out.println(roomService.getAllFreeRoom());

        //Список свободных номеров (сортировать по цене)
        System.out.println("sort free rooms by Price");
        System.out.println(roomService.getFreeRoomSortByPrice());

        //Список свободных номеров (сортировать по вместимости)
        System.out.println("sort free rooms by Capacity");
        System.out.println(roomService.getFreeRoomSortByCapacity());

        //Список свободных номеров (сортировать по количеству звезд)
        System.out.println("sort free rooms by Comfort");
        System.out.println(roomService.getFreeRoomSortByComfort());

        //Список постояльцев и их номеров (сортировать по алфавиту)
        System.out.println("list of all guests and their rooms, sort guests in room by name");
        System.out.println(roomService.getAllGuestsAndRoomsSortByName());
        System.out.println();

        //Список постояльцев и их номеров (сортировать дате освобождения номера)
        System.out.println("list of all guests and their rooms, sort guests in room by departure date");
        System.out.println(roomService.getAllGuestsAndRoomsSortByDeparture());

        //Общее число свободных номеров
        System.out.println("available rooms");
        System.out.println(roomService.availableRooms());

        //Общее число постояльцев
        System.out.println("Total Number  Of Guests");
        System.out.println(guestDao.getTotalNumber());
        //Список номеров которые будут свободны по определенной дате в будущем
        LocalDate onDate = LocalDate.of(2021, 06, 15);
        System.out.println("get free Rooms on Date");
        System.out.println(roomService.getFreeRoomsByDate(onDate));
        //Сумму оплаты за номер которую должен оплатить постоялец
        System.out.println("Bill of payment for the room");
        System.out.println(roomService.getBill(guest));
        // Посмотреть 3-х последних постояльцев номера и даты их пребывания
        System.out.println("Last 3 guests of the room");
        System.out.println(roomService.lastGuestsOfRoom(1));
        //Посмотреть список услуг постояльца и их цену
        System.out.println("All services of guest");
        System.out.println(guestService.getAllServices(guest));
        System.out.println(guestService.getAllServices(guest2));
        System.out.println(guestService.getAllServices(guest11));

        System.out.println();
        //Посмотреть список услуг постояльца и их цену (сортировать по цене) ***----
        System.out.println("get All guest's Services Sort By Price");
        System.out.println(serviceService.getAllServicesSortByPrice(guest));

        //Посмотреть детали отдельного номера
        System.out.println(roomService.getByRoomNumber(1));


    }
}