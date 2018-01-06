package application.repository.streaming;

import application.model.database.VoteRelation;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoteRelationRepository extends GraphRepository<VoteRelation> {

}
