package shpp.com.ua.example.repository;

import org.springframework.data.repository.CrudRepository;
import shpp.com.ua.example.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{
}
