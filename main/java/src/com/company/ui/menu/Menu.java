package com.company.ui.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String name;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        if (menuItems == null){
            menuItems = new ArrayList<>();

        }
        return menuItems;
    }

    public void addMenuItem(MenuItem item) {
        this.menuItems.add(item);
    }

}
