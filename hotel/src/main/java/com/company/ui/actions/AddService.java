package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.model.Guest;
import com.company.util.ScannerUtil;

public class AddService extends AbstractAction {

    public AddService(HotelFacade faсade) {
        super(faсade);
    }

    @Override
    public void execute() {
        System.out.println("введите название услуги");
        String name = ScannerUtil.readString();
        System.out.println("введите цену услуги");
        Double price = ScannerUtil.readDouble();
        System.out.println("введите ID гостя");
        Long guestId = ScannerUtil.readLong();
        hotelFacade.saveService(name, price, guestId);
    }

}