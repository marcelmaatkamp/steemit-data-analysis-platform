package application.repository.neo4j;

import application.model.neo4j.Permlink;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PermlinkRepository extends GraphRepository<Permlink> {
    Permlink findByLink(String permlink);
}