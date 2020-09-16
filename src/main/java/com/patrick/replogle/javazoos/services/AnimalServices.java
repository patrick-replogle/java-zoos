package com.patrick.replogle.javazoos.services;

import com.patrick.replogle.javazoos.models.Animal;
import com.patrick.replogle.javazoos.views.AnimalCounts;

import java.util.List;

public interface AnimalServices
{
    List<AnimalCounts> findAnimalCount();

    Animal findAnimalById(long id);
}
