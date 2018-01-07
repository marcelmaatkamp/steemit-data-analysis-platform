package application.repository.database;

import application.model.database.Author;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AuthorRepository extends GraphRepository<Author> {
    Author findByName(String name);

}
