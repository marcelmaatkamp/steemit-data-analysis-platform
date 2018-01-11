package application.repository.neo4j;

import application.model.neo4j.Account;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AccountRepository extends GraphRepository<Account> {
    Account findByName(String name);
}
