package com.rubotnic.api.service;

import com.rubotnic.api.exception.DataDublicateException;
import com.rubotnic.api.exception.DataNotFoundException;
import com.rubotnic.api.model.dto.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto findById(Integer id) throws DataNotFoundException;

    PersonDto findByEmail(String email) throws DataNotFoundException;

    List<PersonDto> findAll();

    PersonDto create(PersonDto dto) throws DataDublicateException, DataNotFoundException;

    PersonDto update(PersonDto dto) throws DataNotFoundException;

    void delete(Integer id) throws DataNotFoundException;

}
