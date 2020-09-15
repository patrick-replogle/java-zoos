package com.patrick.replogle.javazoos.respository;

import com.patrick.replogle.javazoos.models.Animal;
import com.patrick.replogle.javazoos.views.AnimalCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{
    @Query(value = "SELECT a.animaltype, a.animalid, count(z.zooid) countzoos " +
            "FROM animals a LEFT JOIN zooanimals z " +
            "ON a.animalid = z.animalid " +
            "GROUP BY a.animaltype", nativeQuery = true)
    List<AnimalCounts> findAnimalCount();
}
