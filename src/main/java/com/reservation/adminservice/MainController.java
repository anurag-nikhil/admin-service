package com.reservation.adminservice;

import com.reservation.adminservice.model.Route;
import com.reservation.adminservice.service.RouteService;
import com.reservation.bookingservice.exception.BusResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    private final RouteService busRouteService;

    public MainController(RouteService busRouteService) {
        this.busRouteService = busRouteService;
    }


    @PostMapping("/buses")
    ResponseEntity<Route> saveBus(@RequestBody Route bus) {
        Route busDetail = busRouteService.saveBus(bus);
        return ResponseEntity.status(HttpStatus.CREATED).body(busDetail);
    }

    @PutMapping("/buses")
    ResponseEntity<Route> updateBus(@RequestBody Route bus) {
        Route busDetail = busRouteService.saveBus(bus);
        return ResponseEntity.status(HttpStatus.OK).body(busDetail);
    }

    @GetMapping("/buses")
    ResponseEntity<List<Route>> getAllBuses() {
        List<Route> busDetail = busRouteService.getAllBuses();
        return ResponseEntity.status(HttpStatus.OK).body(busDetail);
    }

    @GetMapping("/buses/{bus_id}")
    ResponseEntity<Route> getBus(@PathVariable("bus_id") Integer busId) {
        Optional<Route> busDetail = busRouteService.getBus(busId);
        if(busDetail.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(busDetail.get());
        }
        else {
            throw new BusResourceNotFoundException(String.format("Bus details with id %d not found",busId));
        }
    }

    @DeleteMapping("/buses/{bus_id}")
    ResponseEntity deleteBus(@PathVariable("bus_id") Integer busId) {
        Route busDetail = busRouteService.deleteBus(busId);
        if(busDetail == null){
            throw new BusResourceNotFoundException(String.format("Bus details with id %d not found",busId));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
