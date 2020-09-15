package com.patrick.replogle.javazoos.services;

import com.patrick.replogle.javazoos.views.AnimalCounts;

import java.util.List;

public interface AnimalServices
{
    List<AnimalCounts> findAnimalCount();
}
