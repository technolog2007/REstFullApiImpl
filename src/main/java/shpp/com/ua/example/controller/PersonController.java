package shpp.com.ua.example.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shpp.com.ua.example.model.Person;
import shpp.com.ua.example.service.PersonService;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Person Controller",
     description = "This is the controller for recording and " +
                "receiving from the DB valid persons with a unique TIN")
@OpenAPIDefinition(info = @Info(title = "Restful API App", version = "1.0.1",
        description = "This program implements the Restful API, and allows you " +
                "to add valid persons to the database, as well as receive information about them."))
public class PersonController {
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private static final String URL = "/person";
    private static final String ADD_ERROR_MESSAGE = "Your person is not valid! Pleas try again!";
    private static final String ERROR_MESSAGE = "Your request is not valid! Pleas try again!";
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(URL)
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(description = "Get all person from DB", summary = "Get all person from DB")
    public List<Person> getAllPerson() {
        logger.info("start getAllPerson method ...");
        return personService.getAllPerson();
    }

    @GetMapping(URL + "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(description = "Returns the person by id from DB", summary = "Returns the person by id from DB")
    public Person getPerson(@PathVariable("id") int id) {
        if (personService.existID(id)) {
            return personService.getPersonById(id);
        } else {
            throw new NotFoundException(ERROR_MESSAGE + "ID # " + id + " does not exist");
        }
    }

    @DeleteMapping(URL + "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(description = "Delete the person by id from DB", summary = "Returns the person by id from DB")
    public String deletePerson(@PathVariable("id") int id) {
        if (personService.existID(id)) {
            personService.delete(id);
            return "delete successful!";
        } else {
            throw new NotFoundException(ERROR_MESSAGE + "ID # " + id + " does not exist");
        }
    }

    @PostMapping(URL)
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(description = "Create valid person in DB", summary = "Create valid person in DB")
    public String savePerson(@Valid @RequestBody Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(ADD_ERROR_MESSAGE);
            return URL;
        }
        personService.saveOrUpdate(person);
        return person.toString();
    }

    @PutMapping(URL)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Makes changes to person fields in the DB", summary = "Makes changes to person fields in the DB")
    public String putPerson(@Valid @RequestBody Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(ADD_ERROR_MESSAGE);
            return URL;
        }
        if (!personService.existID(person.getId())) {
            logger.info("Please try POST request for add new person!");
            return URL;
        }
        personService.saveOrUpdate(person);
        return person.toString();
    }
}
