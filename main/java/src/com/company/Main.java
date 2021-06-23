package com.company;

import com.company.ui.menu.Builder;
import com.company.ui.menu.MenuController;
import com.company.ui.menu.Navigator;

public class Main {

    public static void main(String[] args) {
        Builder builder = new Builder();
        Navigator navigator = new Navigator();
        MenuController menuController = new MenuController(builder, navigator);
        menuController.run();
    }
}