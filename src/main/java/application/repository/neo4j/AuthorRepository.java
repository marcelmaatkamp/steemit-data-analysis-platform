package application.repository.neo4j;

import application.model.neo4j.Author;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AuthorRepository extends GraphRepository<Author> {
    Author findByName(String name);

}
