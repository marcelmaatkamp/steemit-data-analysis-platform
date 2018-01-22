package application.repository.mongodb;

import application.model.mongodb.AccountOperations.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Calendar;

public interface AccountOperationsRepository extends MongoRepository<Vote, String> {
    Vote findByVoter(String name);

    @Query("{'type': 'vote'}")
    Page<Vote> listVotes(Pageable pageable);

    @Query("{'type': 'vote', 'timestamp': ?0 }")
    Page<Vote> listVotesOnDate(Pageable pageable, Calendar calendar);

}
