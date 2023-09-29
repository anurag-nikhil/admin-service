package com.reservation.adminservice.repository;

import com.reservation.adminservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    List<Route> findAllByIsDeleted(boolean isDeleted);
    Optional<Route> findByIdAndIsDeleted(Integer id, boolean isDeleted);
}