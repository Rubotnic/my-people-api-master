package com.rubotnic.api.service.impl;

import com.rubotnic.api.exception.DataDublicateException;
import com.rubotnic.api.exception.DataNotFoundException;
import com.rubotnic.api.model.dto.PersonDto;
import com.rubotnic.api.model.entity.Person;
import com.rubotnic.api.repository.PersonRepository;
import com.rubotnic.api.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    ModelMapper mapper;
    PersonRepository repository;

    public PersonServiceImpl(ModelMapper mapper, PersonRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public PersonDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Person Id should not be null");
        Person result = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Person not found"));
        return mapper.map(result, PersonDto.class);
    }

    @Override
    public PersonDto findByEmail(String email) throws DataNotFoundException {
        if (email == null) throw new IllegalArgumentException("Email should not be null");
        Person result = repository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("Person not found"));
        return mapper.map(result, PersonDto.class);
    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> mapper.map(category, PersonDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public PersonDto create(PersonDto dto) throws DataDublicateException, DataNotFoundException {
        if (dto == null) throw new IllegalArgumentException("Person data should not bu null");
        if (dto.getId() != null) throw new IllegalArgumentException("id should be null");
        if (repository.findByEmail(dto.getEmail()).isPresent()) throw new DataDublicateException("Email is duplicate");
        Person entity = mapper.map(dto, Person.class);
        Person result = repository.save(entity);
        return mapper.map(result, PersonDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public PersonDto update(PersonDto dto) throws DataNotFoundException {
        System.out.println("dto =" + dto);
        if (dto == null) throw new IllegalArgumentException("Person data should not be null");
        if (dto.getId() == null) throw new IllegalArgumentException("id should not be null");
        findById(dto.getId());

        Person entity = mapper.map(dto, Person.class);
        Person result = repository.save(entity);

        return mapper.map(result, PersonDto.class);
    }

    @Override
    public void delete(Integer id) throws DataNotFoundException {
        findById(id);
        repository.deleteById(id);
    }
}
