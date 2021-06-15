package com.company.ui.menu;

import com.company.ui.actions.*;

public class Builder {

    private Menu rootMenu;

    public void buildMenu() {
        Menu mainMenu = new Menu("ГЛАВНОЕ МЕНЮ");
        Menu roomMenu = new Menu("Комнаты");
        Menu guestMenu = new Menu("Гости");
        Menu serviceMenu = new Menu("Услуги");
        mainMenu.addMenuItem(new MenuItem("Комнаты", new SubMenu(), roomMenu));
        mainMenu.addMenuItem(new MenuItem("Гости", new SubMenu(), guestMenu));

        mainMenu.addMenuItem(new MenuItem("Услуги", new SubMenu(), serviceMenu));

        roomMenu.addMenuItem(new MenuItem("Создание комнаты", new AddRoom(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Вывести список комнат", new GetAllRoomAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по цене", new GetAllRoomsSortedByPriceAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по вместимости", new GetAllRoomsSortedByCapacityAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Отсортировать комнаты по комфортности", new GetAllRoomsSortedByComfortAction(), roomMenu));
        roomMenu.addMenuItem(new MenuItem("Главное меню", () -> {
        }, mainMenu));

        guestMenu.addMenuItem(new MenuItem("Добавить гостя", new AddGuest(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Посмотреть список всех гостей", new GetAllGuestsAction(), guestMenu));

        guestMenu.addMenuItem(new MenuItem("Отсортировать гостей имени", new GetAllGuestsSortedByNameAction(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Отсортировать гостей по дате отправления", new GetAllGuestsSortedByDepartureAction(), guestMenu));
        guestMenu.addMenuItem(new MenuItem("Главное меню", () -> {
        }, mainMenu));


        serviceMenu.addMenuItem(new MenuItem("Добавить услугу", new AddService(), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Посмотреть список услуги гостя и их цену  ", new GetAllGuestServices(), serviceMenu));
        serviceMenu.addMenuItem(new MenuItem("Отсортировать услуги по цене)", new GetAllServicesSortedByPrice(), serviceMenu));

        serviceMenu.addMenuItem(new MenuItem("Главное меню", () -> {
        }, mainMenu));


        this.rootMenu = mainMenu;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

}


