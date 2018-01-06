package application.repository;

import application.model.steemj.ExtendedAccount;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface ExtendedAccountRepository extends GraphRepository<ExtendedAccount> {
    ExtendedAccount findByName(String name);
}