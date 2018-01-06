package application.repository;

import application.model.ExtendedAccount;
import application.model.VoteState;
import application.model.json.Vote;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoteStateRepository extends GraphRepository<VoteState> {
    Vote findByVoter(ExtendedAccount voter);

    Vote findByAuthor(ExtendedAccount author);
}