package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.util.ScannerUtil;

public class AddGuest extends AbstractAction {

    public AddGuest(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        System.out.println("введите ФИО гостя");
        String name = ScannerUtil.readString();
        System.out.println("введите планируемое колличество дней пребывания");
        Integer daysOfStay = ScannerUtil.readInteger();
        hotelFacade.saveGuest(name, daysOfStay);
    }

}
