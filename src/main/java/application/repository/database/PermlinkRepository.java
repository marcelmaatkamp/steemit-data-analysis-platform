package application.repository.database;

import application.model.database.Permlink;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PermlinkRepository extends GraphRepository<Permlink> {
    Permlink findByLink(String permlink);
}