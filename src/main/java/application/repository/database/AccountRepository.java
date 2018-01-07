package application.repository.database;

import application.model.database.Account;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AccountRepository extends GraphRepository<Account> {
    Account findByName(String name);
}
