package com.company.controller;

import com.company.api.service.IRoomService;
import com.company.model.Room;
import com.company.model.RoomStatus;
import com.company.model.dto.RoomDto;
import com.company.service.GuestService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final IRoomService roomService;

    private final ModelMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(GuestService.class.getName());

    public RoomController(IRoomService roomService, ModelMapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }

    @PostMapping(path = "/addroom")
    public ResponseEntity<Void> addRoom(@RequestBody RoomDto request) {
        Room room = mapper.map(request, Room.class);
        roomService.save(room);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/checkin")
    public ResponseEntity<Void> checkIn(@RequestParam Long guestId, @RequestParam Long roomId) {
        roomService.checkIn(guestId, roomId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/checkout")
    public ResponseEntity<Void> checkOut(@RequestParam Long guestId, @RequestParam Long roomId) {
        roomService.checkOut(guestId, roomId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getById(@PathVariable Long id) {
        LOGGER.info("received request: /rooms/" + id);
        return ResponseEntity.ok(mapper.map(roomService.getById(id), RoomDto.class));
    }

    @GetMapping(path = "/getbynumber/{num}")
    public ResponseEntity<RoomDto> getByRoomNumber(@PathVariable Integer num) {
        LOGGER.info("received request: /getbynumber/" + num);
        return ResponseEntity.ok(mapper.map(roomService.getByRoomNumber(num), RoomDto.class));
    }

    @GetMapping(path = "/allrooms")
    public ResponseEntity<List<RoomDto>> getAllRooms(@RequestParam(defaultValue = "") String sort) {
        LOGGER.info("received request: /rooms/allrooms ");
        List<RoomDto> list = new ArrayList<>();
        for (Room room : roomService.getAll(sort)) {
            list.add(mapper.map(room, RoomDto.class));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/available")
    public ResponseEntity<List<RoomDto>> getAvailableRooms(@RequestParam(defaultValue = "") String sort) {
        LOGGER.info("received request: /rooms/availablerooms ");
        List<RoomDto> list = new ArrayList<>();
        for (Room room : roomService.getAvailableRooms(sort)) {
            list.add(mapper.map(room, RoomDto.class));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/availableondate")
    public ResponseEntity<List<RoomDto>> getAvailableRoomsOnDate(@RequestParam String date) {
        LOGGER.info("received request: /rooms/availableroomsondate " + date);
        List<RoomDto> list = new ArrayList<>();
        for (Room room : roomService.getAvailableRoomsByDate(date)) {
            list.add(mapper.map(room, RoomDto.class));
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping("/change/status")
    public ResponseEntity<Void> changeRoomStatus(@RequestParam Integer num, @RequestParam Integer status) {
        LOGGER.info("received request: /rooms/changeRoomStatus " + num);
        roomService.changeStatusByRoomNumber(num, RoomStatus.getRoomStatusByNum(status));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change/price")
    public ResponseEntity<Void> changeRoomPrice(@RequestParam Integer num, @RequestParam Double price) {
        LOGGER.info("received request: /rooms/changePrice " + num);
        roomService.changePrice(num, price);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/bill")
    public ResponseEntity<Double> getGuestBill(@RequestParam Long id) {
        LOGGER.info("received request: /rooms/getGuestBill " + id);
        return ResponseEntity.ok(roomService.getBill(id));
    }


}
