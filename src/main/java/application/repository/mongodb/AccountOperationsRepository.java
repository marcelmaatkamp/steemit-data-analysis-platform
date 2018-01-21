package application.repository.mongodb;

import application.model.mongodb.AccountOperations.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AccountOperationsRepository extends MongoRepository<Vote, String> {
    Vote findByVoter(String name);

    @Query(value = "{'type': 'vote'}")
    Page<Vote> listVotes(Pageable pageable);

}
