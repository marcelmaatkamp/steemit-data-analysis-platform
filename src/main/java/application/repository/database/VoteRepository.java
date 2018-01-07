package application.repository.database;

import application.model.database.Vote;
import application.model.database.Voter;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoteRepository extends GraphRepository<Vote> {
    Vote findByVoter(Voter voter);
}
