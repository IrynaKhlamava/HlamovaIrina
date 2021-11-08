package com.company.ui.actions;

import com.company.facade.HotelFacade;
import com.company.model.Room;
import com.company.util.ScannerUtil;


public class GetRoomDescription extends AbstractAction {

    public GetRoomDescription(HotelFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        Integer numRoom = ScannerUtil.readInteger();
        Room room = hotelFacade.getByRoomNumber(numRoom);
    }

}
