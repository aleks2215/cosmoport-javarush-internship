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
//        shipRepository.findAll()
//        shipRepository.f
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
    public Ship createShip(Ship ship) throws BadRequestException {

        if (ship.getName() == null || ship.getPlanet() == null || ship.getShipType() == null || ship.getProdDate() == null
        || ship.getSpeed() == null || ship.getCrewSize() == null) {
            throw new BadRequestException();
        }
        System.out.println();
//        shipRepository.save(ship)
        return null;
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

    @Override
    public Ship getShip(Long id) throws NotFoundException, BadRequestException {
        return shipRepository.findById(id).get();
    }

    @Override
    public Ship updateShip(Long id, Ship ship) throws NotFoundException, BadRequestException {
        return null;
    }

    @Override
    public void deleteShip(Long id) throws NotFoundException, BadRequestException {
        shipRepository.deleteById(id);
    }

    //    public void save(Ship ship) {
//        shipRepository.save(ship);
//    }
//
//    public List<Ship> listAll() {
//        return (List<Ship>) shipRepository.findAll();
//    }
//
//    public Ship get(Long id) {
//        return shipRepository.findById(id).get();
//    }
//
//    public void delete(Long id) {
//        shipRepository.deleteById(id);
//    }
}
