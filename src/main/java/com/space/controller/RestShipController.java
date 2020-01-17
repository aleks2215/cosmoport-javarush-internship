package com.space.controller;

import com.space.exceptions.BadRequestException;
import com.space.exceptions.NotFoundException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import com.space.service.ShipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class RestShipController {
    private ShipService shipService;

    @Autowired
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/ships")
    public ResponseEntity<List<Ship>> findAll(@RequestParam(value = "name", required = false) String name,
                                              @RequestParam(value = "planet", required = false) String planet,
                                              @RequestParam(value = "shipType", required = false) ShipType shipType,
                                              @RequestParam(value = "after", required = false) Long after,
                                              @RequestParam(value = "before", required = false) Long before,
                                              @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                                              @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                                              @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                                              @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                                              @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                                              @RequestParam(value = "minRating", required = false) Double minRating,
                                              @RequestParam(value = "maxRating", required = false) Double maxRating,
                                              @RequestParam(value = "order", required = false, defaultValue = "ID") ShipOrder order,
                                              @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));
////        return resultList.stream()
////                .skip(pageNumber * pageSize)
////                .limit(pageSize)
////                .collect(Collectors.toList());
//        Specification<Ship> specification = Specification.where()

        return new ResponseEntity<>(shipService.getShipsList(pageable).getContent(), HttpStatus.OK);
    }

    @GetMapping("/ships/count")
    public ResponseEntity<Integer> getCount(@RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "planet", required = false) String planet,
                                            @RequestParam(value = "shipType", required = false) ShipType shipType,
                                            @RequestParam(value = "after", required = false) Long after,
                                            @RequestParam(value = "before", required = false) Long before,
                                            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                                            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                                            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                                            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                                            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                                            @RequestParam(value = "minRating", required = false) Double minRating,
                                            @RequestParam(value = "maxRating", required = false) Double maxRating) {


        return new ResponseEntity<>(shipService.getShipsCount(), HttpStatus.OK);
    }

    @PostMapping("/ships")
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        Ship responseShip;
//        try {
        responseShip = shipService.createShip(ship);
//        } catch (BadRequestException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(responseShip, HttpStatus.OK);
    }

    @GetMapping("/ships/{id}")
    public ResponseEntity<Ship> getShipById(@PathVariable String id) {
        Ship responseShip;
//        try {
        Long longId = shipService.checkId(id);
        responseShip = shipService.getShip(longId);
//        } catch (NotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (BadRequestException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(responseShip, HttpStatus.OK);
    }

    @PostMapping("/ships/{id}")
    public ResponseEntity<Ship> updateShip(@PathVariable Long id,
                                           @RequestBody Ship ship) {
        Ship responseShip;
//        try {
            responseShip = this.shipService.updateShip(id, ship);
//        } catch (NotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (BadRequestException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(responseShip, HttpStatus.OK);
    }

    @DeleteMapping("/ships/{id}")
    public ResponseEntity<?> deleteShip(@PathVariable String id) {
//        try {
        Long longId = shipService.checkId(id);

        shipService.deleteShip(longId);
//        } catch (NotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (BadRequestException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
