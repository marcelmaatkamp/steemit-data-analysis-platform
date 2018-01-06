package application.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import application.model.ExtendedSteemjAccount;


public interface ExtendedAccountRepository extends GraphRepository<ExtendedSteemjAccount> {
    ExtendedSteemjAccount findByName(String name);
}