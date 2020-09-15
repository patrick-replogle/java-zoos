package com.patrick.replogle.javazoos.services;

import com.patrick.replogle.javazoos.models.Telephone;
import com.patrick.replogle.javazoos.respository.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "telephoneService")
public class TelephoneServicesImpl implements TelephoneService
{
    @Autowired
    private TelephoneRepository telephonerepos;

    @Override
    public List<Telephone> findAll()
    {
        List<Telephone> list = new ArrayList<>();

        telephonerepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }
}
