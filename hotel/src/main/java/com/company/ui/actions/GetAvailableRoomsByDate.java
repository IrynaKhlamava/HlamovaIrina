package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.util.ScannerUtil;


public class GetAvailableRoomsByDate extends AbstractAction {

    public GetAvailableRoomsByDate(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        System.out.println("введите число");
        String day = ScannerUtil.readString();
        System.out.println("введите месяц");
        String month = ScannerUtil.readString();
        System.out.println("введите год");
        String year = ScannerUtil.readString();
        String byDate = year + '-' + month + '-' + day;
        hotelFacade.getAvailableRoomsByDate(byDate);
    }

}
