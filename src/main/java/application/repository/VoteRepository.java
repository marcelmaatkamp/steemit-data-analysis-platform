package application.repository;

import application.model.json.Vote;
import application.model.steemj.ExtendedAccount;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoteRepository extends GraphRepository<Vote> {
    Vote findByVoter(ExtendedAccount voter);
    Vote findByAuthor(ExtendedAccount author);
}