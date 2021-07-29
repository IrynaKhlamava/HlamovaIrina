package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.util.ScannerUtil;

import java.time.LocalDate;

public class GetFreeRoomsByDate extends AbstractAction {

    public GetFreeRoomsByDate(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        System.out.println("введите число");
        Integer day = ScannerUtil.readInteger();
        System.out.println("введите месяц");
        Integer month = ScannerUtil.readInteger();
        System.out.println("введите год");
        Integer year = ScannerUtil.readInteger();
        LocalDate onDate = LocalDate.of(year, month, day);
        hotelFacade.getFreeRoomsByDate(onDate).forEach(System.out::println);
    }

}
