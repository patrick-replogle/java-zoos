package com.patrick.replogle.javazoos.respository;

import com.patrick.replogle.javazoos.models.ZooAnimals;
import org.springframework.data.repository.CrudRepository;

public interface ZooAnimalsRepository extends CrudRepository<ZooAnimals, Long>
{
}
