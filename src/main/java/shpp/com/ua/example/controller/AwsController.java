package shpp.com.ua.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shpp.com.ua.example.model.Person;
import shpp.com.ua.example.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AwsController {

    private final Logger logger = LoggerFactory.getLogger(PersonController.class);
    // autowired the PersonService class
    @Autowired
    PersonService personService;

    @GetMapping("person/aws/show")
    private String AwsDeploy(){
        return "Deploy success on AWS ...";
    }

    // creating a get mapping that retrieves all the persons detail from the database
    @GetMapping("/person/aws")
    private List<Person> getAllPerson() {
        logger.info("start getAllPerson method ...");
        return personService.getAllPerson();
    }

    // creating a get mapping that retrieves the detail of a specific person
    @GetMapping("/person/aws/{id}")
    private Person getPerson(@PathVariable("id") int id) {
        logger.info("start getPerson method ...");
        return personService.getPersonById(id);
    }

    // creating a delete mapping that deletes a specific person
    @DeleteMapping("/person/aws/{id}")
    private void deletePerson(@PathVariable("id") int id) {
        logger.info("start deletePerson method ...");
        personService.delete(id);
    }

    // creating post mapping that post the person detail in the database
    @PostMapping("/person/aws")
    private int savePerson(@Valid @RequestBody Person person) {
        logger.info("start savePerson method ...");
        personService.saveOrUpdate(person);
        return person.getId();
    }

    @PutMapping("/person/aws")
    private int putPerson(@Valid @RequestBody Person person) {
        logger.info("start put person ... ");
        personService.saveOrUpdate(person);
        return person.getId();
    }
}
