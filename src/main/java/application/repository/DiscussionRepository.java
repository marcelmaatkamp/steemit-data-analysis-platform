package application.repository;

import application.model.Discussion;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface DiscussionRepository extends GraphRepository<Discussion> {

}