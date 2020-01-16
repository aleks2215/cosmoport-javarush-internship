package com.space.service;

import com.space.exceptions.BadRequestException;
import com.space.exceptions.NotFoundException;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShipServiceImpl implements ShipService{
    @Autowired
    ShipRepository shipRepository;

    @Override
    public List<Ship> getShipsList() {
        System.out.println("hi");
//        shipRepository.findAll()
        List<Ship> shipList = shipRepository.findAll();
        for (Ship ship : shipList) {
            System.out.println(ship);
        }

        return shipRepository.findAll();

    }

    @Override
    public Integer getShipsCount() {
        return shipRepository.findAll().size();
    }

    @Override
    public Ship createShip(Ship ship) throws BadRequestException {
        return shipRepository.save(ship);
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
