package application.repository.steemj;

import application.model.steemj.Discussion;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface DiscussionRepository extends GraphRepository<Discussion> {
    Discussion findByPermlink(String permlink);
}