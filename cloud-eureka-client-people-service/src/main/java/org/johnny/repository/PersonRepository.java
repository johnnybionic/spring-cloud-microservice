package org.johnny.repository;

import org.johnny.domain.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for Person.
 *
 * Created by johnny on 04/08/2016.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p.id from Person p")
    List<Long> findAllIds();
}
