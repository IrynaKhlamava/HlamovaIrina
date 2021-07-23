package com.company.ui.menu;

import com.company.injection.annotation.Autowired;
import com.company.injection.annotation.Component;

import java.util.List;

@Component
public class Navigator {

    @Autowired
    private Menu currentMenu;

    public void printMenu() {
        System.out.println(currentMenu.getName());
        List<MenuItem> menuItems = currentMenu.getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + ". " + menuItems.get(i).getTitle());
        }
    }

    public void navigate(Integer index) {
        if (currentMenu != null) {
            MenuItem menuItem = currentMenu.getMenuItems().get(index);
            menuItem.doAction();
            this.currentMenu = menuItem.getNextMenu();
        }
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }


}
