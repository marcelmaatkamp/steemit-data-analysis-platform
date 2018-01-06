package application.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import application.model.ExtendedAccount;


public interface ExtendedAccountRepository extends GraphRepository<ExtendedAccount> {
    ExtendedAccount findByName(String name);
}