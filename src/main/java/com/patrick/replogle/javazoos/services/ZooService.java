package com.patrick.replogle.javazoos.services;

import com.patrick.replogle.javazoos.models.Zoo;

import java.util.List;

public interface ZooService
{
    List<Zoo> findAll();

    Zoo findZooById(long id);

    void delete(long id);

    Zoo save(Zoo zoo);

    Zoo update(Zoo zoo, long id);
}
