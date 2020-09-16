package com.patrick.replogle.javazoos.controllers;

import com.patrick.replogle.javazoos.models.Zoo;
import com.patrick.replogle.javazoos.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @DeleteMapping(value = "/zoo/{zooid}")
    public ResponseEntity<?> deleteZooById(@PathVariable long zooid)
    {
        zooServices.delete(zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/zoo", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewZoo(@Valid @RequestBody Zoo newZoo)
    {
        newZoo.setZooid(0);
        newZoo = zooServices.save(newZoo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{customerid}")
                .buildAndExpand(newZoo.getZooid())
                .toUri();

        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(newZoo, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/zoo/{zooid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateFullZoo(@PathVariable long zooid, @Valid @RequestBody Zoo updateZoo)
    {
        updateZoo.setZooid(zooid);

        updateZoo = zooServices.save(updateZoo);

        return new ResponseEntity<>(updateZoo, HttpStatus.OK);
    }

    @PatchMapping(value = "/zoo/{zooid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updatePartZoo(@PathVariable long zooid, @RequestBody Zoo updateZoo)
    {
        updateZoo = zooServices.update(updateZoo, zooid);

        return new ResponseEntity<>(updateZoo, HttpStatus.OK);
    }

}
