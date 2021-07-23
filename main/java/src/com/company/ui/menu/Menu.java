package com.company.ui.menu;

import com.company.injection.annotation.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Menu {

    private String name;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu() {

    }

    public Menu(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem item) {
        this.menuItems.add(item);
    }

}
