package application.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import application.model.Person;
import application.model.Vote;

public interface VoteRepository extends GraphRepository<Vote> {
    Vote findByVoter(Person person);
}