package com.company;

import com.company.api.dao.IGuestDao;
import com.company.api.dao.IRoomDao;
import com.company.api.service.IGuestService;
import com.company.api.service.IRoomService;
import com.company.dao.GuestDao;
import com.company.dao.RoomDao;
import com.company.model.*;
import com.company.service.GuestService;
import com.company.service.RoomService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Hotel {

    private static final IGuestDao guestDao = new GuestDao();

    private static final IGuestService guestService = new GuestService(guestDao);

    private static final IRoomDao roomDao = new RoomDao();

    private static final IRoomService roomService = new RoomService(roomDao, guestDao);

    public static void main(String[] args) throws ParseException {
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
        // guestDao.delete(guest2);
        // System.out.println("Removed guest2: " + guestDao.getAllGuests());

        Guest guest4 = guestService.addGuest("Guest3", 4);

        List<Guest> guests = new ArrayList<>();

        Room room = roomDao.addRoom(1, 4, RoomStatus.CLEAN, 50.00, RoomComfort.FOUR_STARS, guests);

        List<Guest> guests2 = new ArrayList<>();

        Room room2 = roomDao.addRoom(2, 2, RoomStatus.CLEAN, 75.00, RoomComfort.FIVE_STARS, guests2);

        List<Guest> guests3 = new ArrayList<>();

        Room room3 = roomDao.addRoom(3, 1, RoomStatus.CLEAN, 105.30, RoomComfort.THREE_STARS, guests3);

        List<Guest> guests4 = new ArrayList<>();
        Room room4 = roomDao.addRoom(4, 2, RoomStatus.CLEAN, 125.00, RoomComfort.FOUR_STARS, guests4);

        List<Guest> guests5 = new ArrayList<>();
        Room room5 = roomDao.addRoom(5, 1, RoomStatus.CLEAN, 180.00, RoomComfort.FIVE_STARS, guests5);

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

        System.out.println(roomDao.getAll());
        System.out.println("Guests by room number 2 " + roomService.getByRoomNumber(2));

        //Изменить статус номера на ремонтируемый/обслуживаемый
        roomDao.changeStatus(1, RoomStatus.OUT_OF_ORDER);
        System.out.println("Changed room status ");
        System.out.println(roomDao.getAll());

        roomDao.changePrice(1, 66.50);
        //Изменить цену номера или услуги
        System.out.println("Changed price room number 1 ");
        System.out.println(roomService.getByRoomNumber(1));

        System.out.println();

        //Добавить услугу
        guestService.addServices(guest, AdditionalServices.BEAUTY_SALON);
        guestService.addServices(guest, AdditionalServices.BEAUTY_SALON);
        guestService.addServices(guest, AdditionalServices.GYM);
        System.out.println(roomDao.getAll());

        //Список номеров (сортировать по вместимости)
        System.out.println("sort by Capacity");
        System.out.println(roomService.sortRoomByCapacity());
        System.out.println("all rooms");
        System.out.println(roomDao.getAll());

        //Список номеров (сортировать по цене)
        System.out.println("sort by Price");
        System.out.println(roomService.sortRoomByPrice());
        System.out.println("all rooms");
        System.out.println(roomDao.getAll());

        //Список номеров (сортировать по количеству звезд)
        System.out.println("sort by Comfort");
        System.out.println(roomService.sortRoomByComfort());
        System.out.println("all rooms");
        System.out.println(roomDao.getAll());

        //Список свободных номеров
        System.out.println("all free room");
        List<Room> freeRoom = roomDao.getAllFreeRoom();
        System.out.println(freeRoom);

        //Список свободных номеров (сортировать по цене)
        System.out.println("sort free rooms by Price");
        System.out.println(roomService.sortFreeRoomByPrice(freeRoom));

        //Список свободных номеров (сортировать по вместимости)
        System.out.println("sort free rooms by Capacity");
        System.out.println(roomService.sortFreeRoomByCapacity(freeRoom));

        //Список свободных номеров (сортировать по количеству звезд)
        System.out.println("sort free rooms by Comfort");
        System.out.println(roomService.sortFreeRoomByComfort(freeRoom));

        //Список постояльцев и их номеров (сортировать по алфавиту)
        System.out.println("list of all guests and their rooms, sort guests in room by name");
        roomService.getAllGuestsAndRooms();

        //Список постояльцев и их номеров (сортировать дате освобождения номера)
        System.out.println("list of all guests and their rooms, sort guests in room by departure date");
        roomService.getAllGuestsAndRoomsSortByDeparture();

        //Общее число свободных номеров
        System.out.println("available rooms");
        System.out.println(roomService.availableRooms());

        //Общее число постояльцев
        System.out.println("Total Number  Of Guests");
        System.out.println(guestDao.getTotalNumber());
        //Список номеров которые будут свободны по определенной дате в будущем
       /* Date onDate = new SimpleDateFormat("yyyy-mm-dd").parse("2021-06-20");
        System.out.println(roomService.getFreeRoomsByDate(onDate));*/
        //Сумму оплаты за номер которую должен оплатить постоялец
        System.out.println("Bill of payment for the room");
        System.out.println(roomService.getBill(guest));
        // Посмотреть 3-х последних постояльцев номера и даты их пребывания
        System.out.println("Last 3 guests of the room");
        roomService.lastGuestsOfRoom(1);
        //Посмотреть список услуг постояльца и их цену
        guestService.getAllAddServices(guest);
        System.out.println();

        //Посмотреть список услуг постояльца и их цену (сортировать по цене)
        guestService.getAllAddServicesSortByPrice(guest);

        //Посмотреть детали отдельного номера
        System.out.println(roomService.getByRoomNumber(1));


    }
}
