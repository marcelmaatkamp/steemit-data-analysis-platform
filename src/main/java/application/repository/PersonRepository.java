package application.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import application.model.Person;

@Repository
public interface PersonRepository extends GraphRepository<Person> {
    Person findByName(String name);
}