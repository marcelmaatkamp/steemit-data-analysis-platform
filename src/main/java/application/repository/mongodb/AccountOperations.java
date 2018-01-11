package application.repository.mongodb;

import application.model.mongodb.AccountOperations.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountOperations extends MongoRepository<Vote, String> {
    public Vote findByVoter(String name);
}
