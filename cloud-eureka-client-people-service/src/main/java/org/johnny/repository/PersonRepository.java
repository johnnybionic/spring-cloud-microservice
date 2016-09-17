package org.johnny.repository;

import org.johnny.domain.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     * Find all Person IDs.
     * @return a list of the IDs
     */
    @Query("select p.id from Person p")
    List<Long> findAllIds();
}
