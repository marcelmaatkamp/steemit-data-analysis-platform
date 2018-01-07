package application.repository.steemj;

import application.model.json.Vote;
import application.model.steemj.ExtendedAccount;
import application.model.steemj.VoteState;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoteStateRepository extends GraphRepository<VoteState> {
    Vote findByVoter(ExtendedAccount voter);
}