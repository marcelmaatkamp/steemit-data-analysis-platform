package application.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import application.model.Person;
import application.model.Vote;

@Repository
public interface VoteRepository extends GraphRepository<Vote> {
    Vote findByVoter(Person person);
}