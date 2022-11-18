package com.rubotnic.api.repository;

import com.rubotnic.api.model.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();

    Optional<Person> findByEmail (String email);
}
