package application.repository.database;

import application.model.database.Voter;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoterRepository extends GraphRepository<Voter> {
    Voter findByName(String name);
}
