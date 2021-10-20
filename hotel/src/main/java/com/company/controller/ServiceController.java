package com.company.controller;

import com.company.api.service.IServiceService;
import com.company.model.Service;
import com.company.model.dto.ServiceDto;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ModelMapper mapper;

    private final IServiceService serviceService;

    private static final Logger LOGGER = Logger.getLogger(ServiceController.class.getName());


    public ServiceController(IServiceService serviceService, ModelMapper mapper) {
        this.mapper = mapper;
        this.serviceService = serviceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getById(@PathVariable Long id) {
        LOGGER.info("received request: /services/" + id);
        return ResponseEntity.ok(mapper.map(serviceService.getById(id), ServiceDto.class));
    }

    @PostMapping(path = "/addservice")
    public ResponseEntity<Void> addService(@RequestBody ServiceDto request) {
        serviceService.addService(request.getName(), request.getPrice(), request.getGuestId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/getall")
    public ResponseEntity<List<ServiceDto>> getAll(@RequestParam(defaultValue = "") String sort) {
        LOGGER.info("received request: /services/getall ");
        List<ServiceDto> list = new ArrayList<>();
        for (Service service : serviceService.getAll(sort)) {
            list.add(mapper.map(service, ServiceDto.class));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/getguestservices/{id}")
    public ResponseEntity<List<ServiceDto>> getGuestServices(@PathVariable Long id, @RequestParam(defaultValue = "") String sort) {
        LOGGER.info("received request: /services/getguestservices " + id + " sort by " + sort);
        List<ServiceDto> list = new ArrayList<>();
        for (Service service : serviceService.getAllGuestServicesSort(id, sort)) {
            list.add(mapper.map(service, ServiceDto.class));
        }
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("received request: /services/delete " + id);
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ServiceDto request) {
        LOGGER.info("received request: /services/update " + id);
        serviceService.update(id, mapper.map(request, Service.class));
        return ResponseEntity.noContent().build();
    }
}
