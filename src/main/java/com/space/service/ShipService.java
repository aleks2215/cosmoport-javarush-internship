package com.space.service;

import com.space.exceptions.BadRequestException;
import com.space.exceptions.NotFoundException;
import com.space.model.Ship;

import java.util.List;

public interface ShipService {

    List<Ship> getShipsList();
    Integer getShipsCount();
    Ship createShip(Ship ship) throws BadRequestException;
    Ship getShip(Long id) throws NotFoundException, BadRequestException;
    Ship updateShip(Long id, Ship ship) throws NotFoundException, BadRequestException;
    void deleteShip(Long id) throws NotFoundException, BadRequestException;
}
