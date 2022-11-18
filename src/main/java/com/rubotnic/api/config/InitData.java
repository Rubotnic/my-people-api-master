package com.rubotnic.api.config;

import com.rubotnic.api.model.entity.Person;
import com.rubotnic.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    PersonRepository repository;

    @Override
    public void run (String... args) throws Exception {

        repository.save(new Person("Åsa", "Jonsson", "asa.jonsson@lexicon.se", "C# Teacher"));
        repository.save(new Person("Simon", "Elbrink", "simon.elbrink@lexicon.se", "Java Teacher"));
        repository.save(new Person("Mehrdad", "Javan", "mehrdad.javan@lexicon.se", "Java Teacher"));
        repository.save(new Person("Erik", "Svensson", "erik.svensson@lexicon.se", "Java Teacher"));

    }
}
