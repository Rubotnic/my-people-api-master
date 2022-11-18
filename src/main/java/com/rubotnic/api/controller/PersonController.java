package com.rubotnic.api.controller;

import com.rubotnic.api.exception.DataDublicateException;
import com.rubotnic.api.exception.DataNotFoundException;
import com.rubotnic.api.model.dto.PersonDto;
import com.rubotnic.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/person")
@CrossOrigin("*")
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        System.out.println("Get All API has been executed");
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto dto) throws DataDublicateException, DataNotFoundException {
        System.out.println("dto = " + dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity <PersonDto> update(@RequestBody @Valid PersonDto dto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws DataNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
