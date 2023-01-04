package shpp.com.ua.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shpp.com.ua.example.model.Person;
import shpp.com.ua.example.service.PersonService;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private static final String URL = "/person";
    private static final String ADD_ERROR_MESSAGE = "Your person is not valid! Pleas try again!";
    private static final String ERROR_MESSAGE = "Your request is not valid! Pleas try again!";
    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(URL)
    public List<Person> getAllPerson() {
        logger.info("start getAllPerson method ...");
        return personService.getAllPerson();
    }

    @GetMapping(URL + "/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        if (!personService.existID(id)) {
            throw new NotFoundException(ERROR_MESSAGE + id + "does not exist");
        }
        return personService.getPersonById(id);
    }

    @DeleteMapping(URL + "/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        if (!personService.existID(id)) {
            throw new NotFoundException(ERROR_MESSAGE + id + "does not exist");
        }
        personService.delete(id);
        return "delete successful!";
    }

    @PostMapping(URL)
    public String savePerson(@Valid @RequestBody Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(ADD_ERROR_MESSAGE);
            return URL;
        }
        personService.saveOrUpdate(person);
        return person.toString();
    }

    @PutMapping(URL)
    public String putPerson(@Valid @RequestBody Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(ADD_ERROR_MESSAGE);
            return URL;
        }
        if (!personService.existID(person.getId())){
            logger.info("Please try POST request for add new person!");
            return URL;
        }
        personService.saveOrUpdate(person);
        return person.toString();
    }
}
