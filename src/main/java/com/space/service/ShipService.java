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
    Ship createShip(Ship ship);
    Ship getShip(Long id);
    Long checkId(String id);
    Ship updateShip(Long id, Ship ship);
    void deleteShip(Long id);
}
