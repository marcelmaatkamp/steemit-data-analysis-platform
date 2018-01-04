package application.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import application.model.Person;

public interface PersonRepository extends GraphRepository<Person> {
    Person findByName(String name);
}