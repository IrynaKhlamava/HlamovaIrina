package com.company.ui.menu;

import com.company.facade.HotelFacade;

import com.company.ui.actions.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Builder {

    private Menu rootMenu;

    public HotelFacade hotelFacade;

    @Value("${changeStatus}")
    private Boolean changeStatus;

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu(HotelFacade hotelFacade) {
        this.hotelFacade = hotelFacade;
        Menu mainMenu = new Menu("ГЛАВНОЕ МЕНЮ");
        Menu roomMenu = new Menu("Комнаты");
        Menu guestMenu = new Menu("Гости");
        Menu serviceMenu = new Menu("Услуги");
        Menu exit = new Menu("Выход");

        mainMenu.addMenuItem(new MenuItem("Комнаты", new EmptyAction(hotelFacade), roomMenu));
        mainMenu.addMenuItem(new MenuItem("Гости", new EmptyAction(hotelFacade), guestMenu));
        mainMenu.addMenuItem(new MenuItem("Услуги", new EmptyAction(hotelFacade), serviceMenu));
        mainMenu.addMenuItem(new MenuItem("Выход", new ExitAction(hotelFacade), exit));

        roomMenu.addMenuItem(new MenuItem("Добавить комнату", new AddRoom(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Вывести список комнат", new GetAllRoomAction(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по цене", new GetAllRoomsSortedByPriceAction(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по вместимости", new GetAllRoomsSortedByCapacityAction(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по комфортности", new GetAllRoomsSortedByComfortAction(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Количество свободных комнат", new GetAllFreeRoomsAction(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Сводобные комнаты отсортировать по цене",
                new GetFreeRoomsSortedByPrice(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Сводобные комнаты отсортировать по вместимости",
                new GetFreeRoomsSortedByCapacity(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Сводобные комнаты отсортировать по комфортности",
                new GetFreeRoomsSortedByComfort(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Список номеров которые будут свободны к дате", new GetFreeRoomsByDate(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Посмотреть 3-х последних постояльцев номера и даты их пребывания", new GetLastGuestsOfRoom(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Посмотреть детали номера", new GetRoomDescription(hotelFacade), roomMenu));
        if (changeStatus) {
            roomMenu.addMenuItem(new MenuItem("изменить статус номера", new ChangeStatusByRoomNumber(hotelFacade),
                    roomMenu));
        }
        roomMenu.addMenuItem(new MenuItem("изменить цену номера", new ChangeRoomPrice(hotelFacade), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Главное меню", new EmptyAction(hotelFacade), mainMenu));

        guestMenu.addMenuItem(new MenuItem("Добавить гостя", new AddGuest(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Посмотреть список всех гостей", new GetAllGuestsAction(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Заселить гостя в комнату", new CheckInAction(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Выселить гостя из комнаты", new CheckOutAction(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Посмотреть сумму счета гостя", new GetBill(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Посмотреть список услуг гостя и их цену", new GetAllGuestsServices(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Услуги гостя отсортировать по цене", new GetAllGuestServicesSortedByPrice(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Отсортировать гостей по имени", new GetAllGuestsSortedByNameAction(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Отсортировать гостей по дате отправления", new GetAllGuestsSortedByDepartureAction(hotelFacade), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Главное меню", new EmptyAction(hotelFacade), mainMenu));

        serviceMenu.addMenuItem(new MenuItem("Добавить услугу гостю", new AddService(hotelFacade), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Список всех услуг", new GetAllServices(hotelFacade), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Отсортировать все услуги по цене", new GetAllServicesSortedByPrice(hotelFacade), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Отсортировать все услуги по названию", new GetAllServicesSortedByName(hotelFacade), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Главное меню", new EmptyAction(hotelFacade), mainMenu));

        this.rootMenu = mainMenu;
    }
}


