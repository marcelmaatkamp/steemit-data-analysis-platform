package application.repository;

import application.model.steemj.Permlink;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PermlinkRepository extends GraphRepository<Permlink> {
    Permlink findByLink(String permlink);
}