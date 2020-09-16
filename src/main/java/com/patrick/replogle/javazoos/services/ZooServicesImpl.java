package com.patrick.replogle.javazoos.services;

import com.patrick.replogle.javazoos.models.Animal;
import com.patrick.replogle.javazoos.models.Telephone;
import com.patrick.replogle.javazoos.models.Zoo;
import com.patrick.replogle.javazoos.models.ZooAnimals;
import com.patrick.replogle.javazoos.respository.AnimalRepository;
import com.patrick.replogle.javazoos.respository.TelephoneRepository;
import com.patrick.replogle.javazoos.respository.ZooRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service(value = "zooService")
public class ZooServicesImpl implements ZooService
{
    @Autowired
    private ZooRepository zoorepos;

    @Autowired
    private AnimalRepository animalrepos;

    @Autowired
    private TelephoneRepository telephoneRepository;

    @Autowired
    private AnimalServices animalServices;

    @Override
    public List<Zoo> findAll()
    {
        List<Zoo> list = new ArrayList<>();

        zoorepos.findAll()
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Override
    public Zoo findZooById(long id) throws EntityExistsException
    {
        return zoorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zoo " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (zoorepos.findById(id).isPresent())
        {
            zoorepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Zoo " + id + " not found!");
        }
    }

    @Transactional
    @Override
    public Zoo save(Zoo zoo)
    {
        Zoo newZoo = new Zoo();

        if (zoo.getZooid() != 0)
        {
            findZooById(zoo.getZooid());
            newZoo.setZooid(zoo.getZooid());
        }

        newZoo.setZooname(zoo.getZooname());

        // telephones
        newZoo.getTelephones().clear();
        for (Telephone t : zoo.getTelephones())
        {
            Telephone newTelephone = new Telephone(
                    t.getPhonetype(),
                    t.getPhonenumber(),
                    newZoo);

            newZoo.getTelephones().add(newTelephone);
        }

        // animals
        newZoo.getAnimals().clear();
        for (ZooAnimals z : zoo.getAnimals())
        {
            Animal animal = animalServices.findAnimalById(z.getAnimal().getAnimalid());
            newZoo.getAnimals().add(new ZooAnimals(newZoo, animal, null));
        }

        return zoorepos.save(newZoo);
    }

    @Transactional
    @Override
    public Zoo update(Zoo zoo, long id)
    {
        Zoo updateZoo = findZooById(id);

        if (zoo.getZooname() != null)
        {
            updateZoo.setZooname(zoo.getZooname());
        }

        // telephones
        if (zoo.getTelephones().size() > 0)
        {
            updateZoo.getTelephones().clear();
            for (Telephone t : zoo.getTelephones())
            {
                Telephone newTelephone = new Telephone(
                        t.getPhonetype(),
                        t.getPhonenumber(),
                        updateZoo);

                updateZoo.getTelephones().add(newTelephone);
            }
        }


        // animals
        if (zoo.getAnimals().size() > 0)
        {
            updateZoo.getAnimals().clear();
            for (ZooAnimals z : zoo.getAnimals())
            {
                Animal animal = animalServices.findAnimalById(z.getAnimal().getAnimalid());
                updateZoo.getAnimals().add(new ZooAnimals(updateZoo, animal, null));
            }
        }
        return zoorepos.save(updateZoo);
    }
}
