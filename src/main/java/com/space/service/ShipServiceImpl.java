package com.space.service;

import com.space.exceptions.BadRequestException;
import com.space.exceptions.NotFoundException;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class ShipServiceImpl implements ShipService {
    @Autowired
    ShipRepository shipRepository;

    @Override
    public Page<Ship> getShipsList(Pageable sortedBy) {
        System.out.println("hi");

        Page<Ship> shipList = shipRepository.findAll(sortedBy);
        for (Ship ship : shipList) {
            System.out.println(ship);
        }

        return shipRepository.findAll(sortedBy);
    }

    @Override
    public Integer getShipsCount() {
        return shipRepository.findAll().size();
    }

    @Override
    public Ship createShip(Ship ship) {
        if (ship.getName() == null || ship.getPlanet() == null || ship.getShipType() == null || ship.getProdDate() == null
                || ship.getSpeed() == null || ship.getCrewSize() == null) {
            throw new BadRequestException();
        }

        checkShipParams(ship);

        if (ship.getUsed() == null) {
            ship.setUsed(false);
        }

        ship.setRating(computeRating(ship));

        return shipRepository.save(ship);
    }

    private void checkShipParams(Ship ship) {
        String name = ship.getName().trim();
        if (name.length() < 1 || name.length() > 50) {
            throw new BadRequestException();
        }

        String planet = ship.getPlanet().trim();
        if (planet.length() < 1 || planet.length() > 50) {
            throw new BadRequestException();
        }

        Double speed = ship.getSpeed();
        if (speed < 0.01 || speed > 0.99) {
            throw new BadRequestException();
        }

        Integer crewSize = ship.getCrewSize();
        if (crewSize < 1 || crewSize > 9999) {
            throw new BadRequestException();
        }

        Date prodDate = ship.getProdDate();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(prodDate);
        int year = calendar.get(Calendar.YEAR);
        if (year < 2800 || year > 3019) {
            throw new BadRequestException();
        }
    }

    private Double computeRating(Ship ship) {
        double k = ship.getUsed() ? 0.5 : 1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(ship.getProdDate());
        int prodYear = calendar.get(Calendar.YEAR);
        BigDecimal rating = BigDecimal.valueOf((80 * ship.getSpeed() * k) / (3019 - prodYear + 1)).setScale(2, RoundingMode.HALF_UP);
        return rating.doubleValue();
    }

    @Override
    public Ship getShip(Long id) {
        if (!shipRepository.existsById(id)) {
            throw new NotFoundException();
        }

        return shipRepository.findById(id).get();
    }

    @Override
    public Long checkId(String id) {
        Long longId = null;

        if (id == null || id.equals("") || id.equals("0")) {
            throw new BadRequestException();
        }

        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException();
        }

        if (longId < 0) {
            throw new BadRequestException();
        }

        return longId;
    }

    @Override
    public Ship updateShip(Long id, Ship ship) {
        return null;
    }

    @Override
    public void deleteShip(Long id) {
        if (!shipRepository.existsById(id)) {
            throw new NotFoundException();
        }

        shipRepository.deleteById(id);
    }
}
