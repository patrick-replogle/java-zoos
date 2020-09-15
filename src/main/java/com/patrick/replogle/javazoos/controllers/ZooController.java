package com.patrick.replogle.javazoos.controllers;

import com.patrick.replogle.javazoos.models.Zoo;
import com.patrick.replogle.javazoos.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zoos")
public class ZooController
{
    @Autowired
    private ZooService zooServices;


    @GetMapping(value = "/zoos",
            produces = {"application/json"})
    public ResponseEntity<?> listAllZoos()
    {
        List<Zoo> zoos = zooServices.findAll();
        return new ResponseEntity<>(zoos, HttpStatus.OK);
    }

    @GetMapping(value = "/zoo/{zooid}", produces = "application/json")
    public ResponseEntity<?> getZooById(@PathVariable long zooid)
    {
        Zoo z = zooServices.findZooById(zooid);

        return new ResponseEntity<>(z, HttpStatus.OK);
    }
}
