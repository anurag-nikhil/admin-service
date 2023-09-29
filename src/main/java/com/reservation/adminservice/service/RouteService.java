package com.reservation.adminservice.service;

import com.reservation.adminservice.model.Route;
import com.reservation.adminservice.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository busRouteRepository;

    public RouteService(RouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    public Route saveBus(Route bus) {
        return busRouteRepository.saveAndFlush(bus);
    }

    public List<Route> getAllBuses() {
        return busRouteRepository.findAllByIsDeleted(false);
    }

    public Optional<Route> getBus(Integer busId) {
        return busRouteRepository.findByIdAndIsDeleted(busId,false);
    }

    public Route deleteBus(Integer busId) {
        Route busDetail = busRouteRepository.findByIdAndIsDeleted(busId,false).orElse(null);
        if(busDetail!=null){
            busDetail.setIsDeleted(true);
            busRouteRepository.saveAndFlush(busDetail);
            return busDetail;
        }
        return null;
    }
}
