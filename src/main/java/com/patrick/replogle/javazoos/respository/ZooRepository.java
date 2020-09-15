package com.patrick.replogle.javazoos.respository;

import com.patrick.replogle.javazoos.models.Zoo;
import org.springframework.data.repository.CrudRepository;

public interface ZooRepository extends CrudRepository<Zoo, Long>
{

}
