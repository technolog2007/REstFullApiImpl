package shpp.com.ua.example.service;

import org.springframework.stereotype.Service;
import shpp.com.ua.example.model.Person;
import shpp.com.ua.example.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    final
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    public Person getPersonById(int id) {
        return personRepository.findById(id).get();
    }

    public void saveOrUpdate(Person person) {
        personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public boolean existID(int id){
        return personRepository.existsById(id);
    }
}
