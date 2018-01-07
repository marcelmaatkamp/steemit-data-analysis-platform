package application.repository.database;

import application.model.database.Vote;
import application.model.steemj.ExtendedAccount;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoteRepository extends GraphRepository<Vote> {
    application.model.json.Vote findByVoter(ExtendedAccount voter);
}
