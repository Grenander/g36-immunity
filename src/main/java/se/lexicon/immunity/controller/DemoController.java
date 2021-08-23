package se.lexicon.immunity.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.immunity.model.Person;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class DemoController {

    private List<Person> people;


    public DemoController() throws IOException {
        people = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        people = objectMapper.readValue(new File("src/main/resources/static/people.json"), new TypeReference<List<Person>>() {});
    }

    @GetMapping("/api/demo/greeting")
    public String hello(@RequestParam(value = "name", defaultValue = "world") String name){
        return "Hello " + name + " for real! (this time)";
    }

    @GetMapping("/api/demo/people")
    public ResponseEntity<List<Person>> getPeople(){
        return ResponseEntity.ok(people);
    }

    @PostMapping("/api/demo/people")
    public ResponseEntity<Person> create(@RequestBody Person person){
        person.setId(UUID.randomUUID().toString());
        people.add(person);
        return ResponseEntity
                .created(URI.create("/api/demo/people/"+person.getId()))
                .body(person);
    }

    @GetMapping("/api/demo/people/{id}")
    public ResponseEntity<Person> findById(@PathVariable(name = "id") String personId){
        Optional<Person> optional = people.stream()
                .filter(person -> person.getId().equals(personId))
                .findFirst();

        if(optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/demo/people/{id}")
    public ResponseEntity<Person> update(@PathVariable(name = "id") String personId, @RequestBody Person updated){
        Person person = people.stream()
                .filter(p -> p.getId().equals(personId)).findFirst()
                .orElseThrow(RuntimeException::new);

        person.setFirstName(updated.getFirstName());
        person.setLastName(updated.getLastName());
        person.setGender(updated.getGender());

        if(updated.getHobbies() != null){
            person.setHobbies(updated.getHobbies());
        }

        if(updated.getAddress() != null){
            person.setAddress(updated.getAddress());
        }

        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/api/demo/people/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String personId){
        people = people.stream()
                .filter(person -> !person.getId().equals(personId))
                .collect(Collectors.toList());

        boolean deleted = people.stream().noneMatch(person -> person.getId().equals(personId));
        if(deleted){
            return ResponseEntity.ok().build();
        }else {
            throw new RuntimeException("Error: person with id " + personId + " was not deleted");
        }
    }

}
