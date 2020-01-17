package com.space.service;

import com.space.exceptions.BadRequestException;
import com.space.exceptions.NotFoundException;
import com.space.model.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShipService {

    Page<Ship> getShipsList(Pageable sortedBy);
    Integer getShipsCount();
    Ship createShip(Ship ship) throws BadRequestException;
    Ship getShip(Long id) throws NotFoundException, BadRequestException;
    Ship updateShip(Long id, Ship ship) throws NotFoundException, BadRequestException;
    void deleteShip(Long id) throws NotFoundException, BadRequestException;
}
