package com.patrick.replogle.javazoos.services;

import com.patrick.replogle.javazoos.models.Animal;
import com.patrick.replogle.javazoos.respository.AnimalRepository;
import com.patrick.replogle.javazoos.views.AnimalCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "animalService")
public class AnimalServicesImpl implements AnimalServices
{
    @Autowired
    AnimalRepository animalrepos;

    @Override
    public List<AnimalCounts> findAnimalCount()
    {
        List<AnimalCounts> list = animalrepos.findAnimalCount();

        return list;
    }

    @Override
    public Animal findAnimalById(long id)
    {
        return animalrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal " + id + " not found!"));
    }
}
