package com.patrick.replogle.javazoos.controllers;

import com.patrick.replogle.javazoos.respository.AnimalRepository;
import com.patrick.replogle.javazoos.services.AnimalServices;
import com.patrick.replogle.javazoos.views.AnimalCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController
{
    @Autowired
    private AnimalServices animalServices;

    @GetMapping(value = "/animals/count", produces = {"application/json"})
    public ResponseEntity<?> findAnimalCount()
    {
        List<AnimalCounts> rtnList = animalServices.findAnimalCount();

        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
}
