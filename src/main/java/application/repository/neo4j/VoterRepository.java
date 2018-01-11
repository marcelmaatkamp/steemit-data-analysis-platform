package application.repository.neo4j;

import application.model.neo4j.Voter;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoterRepository extends GraphRepository<Voter> {
    Voter findByName(String name);
}
