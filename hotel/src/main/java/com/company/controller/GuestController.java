package com.company.controller;

import com.company.api.service.IGuestService;
import com.company.model.Guest;
import com.company.model.Service;
import com.company.model.dto.GuestDto;
import com.company.model.dto.ServiceDto;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/guests")
public class GuestController {

    private final IGuestService guestService;

    private final ModelMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(GuestController.class.getName());

    public GuestController(IGuestService guestService, ModelMapper mapper) {
        this.guestService = guestService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getById(@PathVariable Long id) {
        LOGGER.info("received request: /guests/" + id);
        return ResponseEntity.ok(mapper.map(guestService.getById(id), GuestDto.class));
    }

    @PostMapping(path = "/addguest")
    public ResponseEntity<Void> addGuest(@RequestBody GuestDto request) {
        guestService.addGuest(request.getName(), request.getDaysOfStay());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/services/{guestId}")
    public ResponseEntity<Set<ServiceDto>> getAllServices(@PathVariable Long guestId) {
        LOGGER.info("received request: /guests/services/" + guestId);
        Set<ServiceDto> servicesDto = new HashSet<>();
        for (Service service : guestService.getAllServices(guestId)) {
            servicesDto.add(mapper.map(service, ServiceDto.class));
        }
        return ResponseEntity.ok(servicesDto);
    }

    @GetMapping("/lastguestsofroom/{num}")
    public ResponseEntity<List<GuestDto>> lastGuestsOfRoom(@PathVariable Integer num) {
        LOGGER.info("received request: /guests/lastGuestsOfRoom " + num);
        List<GuestDto> list = new ArrayList<>();
        for (Guest guest : guestService.lastGuestsOfRoom(num)) {
            list.add(mapper.map(guest, GuestDto.class));
        }
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("received request: /guests/delete " + id);
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody GuestDto request) {
        LOGGER.info("received request: /guests/update " + id);
        guestService.update(id, mapper.map(request, Guest.class));
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/getall")
    public ResponseEntity<List<GuestDto>> getAll(@RequestParam(defaultValue = "") String sort) {
        LOGGER.info("received request: /guests/getall ");
        List<GuestDto> list = new ArrayList<>();
        for (Guest guest : guestService.getAll(sort)) {
            list.add(mapper.map(guest, GuestDto.class));
        }
        return ResponseEntity.ok(list);
    }
}
