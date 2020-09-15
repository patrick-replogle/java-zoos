package com.patrick.replogle.javazoos.services;

import com.patrick.replogle.javazoos.models.Zoo;
import com.patrick.replogle.javazoos.respository.AnimalRepository;
import com.patrick.replogle.javazoos.respository.ZooRepository;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "zooService")
public class ZooServicesImpl implements ZooService
{
    @Autowired
    private ZooRepository zoorepos;

    @Autowired
    private AnimalRepository animalrepos;

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
}
