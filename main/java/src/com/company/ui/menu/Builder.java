package com.company.ui.menu;

import com.company.ui.actions.*;

public class Builder {

    private Menu rootMenu;

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        Menu mainMenu = new Menu("ГЛАВНОЕ МЕНЮ");
        Menu roomMenu = new Menu("Комнаты");
        Menu guestMenu = new Menu("Гости");
        Menu serviceMenu = new Menu("Услуги");

        mainMenu.addMenuItem(new MenuItem("Комнаты", new EmptyAction(), roomMenu));
        mainMenu.addMenuItem(new MenuItem("Гости", new EmptyAction(), guestMenu));
        mainMenu.addMenuItem(new MenuItem("Услуги", new EmptyAction(), serviceMenu));

        roomMenu.addMenuItem(new MenuItem("Добавить комнату", new AddRoom(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Вывести список комнат", new GetAllRoomAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по цене", new GetAllRoomsSortedByPriceAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по вместимости", new GetAllRoomsSortedByCapacityAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по комфортности", new GetAllRoomsSortedByComfortAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Количество свободных комнат", new GetAllFreeRoomsAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Список номеров которые будут свободны к дате", new GetFreeRoomsByDate(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Посмотреть 3-х последних постояльцев номера и даты их пребывания", new GetLastGuestsOfRoom(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Посмотреть детали номера", new GetRoomDescription(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Изменить статус номера", new ChangeStatusByRoomNumber(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Изменить цену номера", new ChangeRoomPrice(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Главное меню", new EmptyAction(), mainMenu));

        guestMenu.addMenuItem(new MenuItem("Добавить гостя", new AddGuest(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Заселить гостя в комнату", new CheckInAction(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Выселить гостя из комнаты", new CheckOutAction(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Посмотреть сумму счета гостя", new GetBill(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Посмотреть список услуг гостя и их цену", new GetAllGuestsServices(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Услуги гостя отсортировать по цене", new GetAllGuestServicesSortedByPrice(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Посмотреть список всех гостей", new GetAllGuestsAction(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Отсортировать гостей по имени", new GetAllGuestsSortedByNameAction(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Отсортировать гостей по дате отправления", new GetAllGuestsSortedByDepartureAction(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Главное меню", new EmptyAction(), mainMenu));

        serviceMenu.addMenuItem(new MenuItem("Добавить услугу гостю", new AddService(), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Список всех услуг", new GetAllServices(), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Отсортировать все услуги по цене", new GetAllServicesSortedByPrice(), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Отсортировать все услуги по названию", new GetAllServicesSortedByName(), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Главное меню", new EmptyAction(), mainMenu));

        this.rootMenu = mainMenu;
    }
}


