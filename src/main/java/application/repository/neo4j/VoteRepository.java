package application.repository.neo4j;

import application.model.neo4j.Vote;
import application.model.neo4j.Voter;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VoteRepository extends GraphRepository<Vote> {

    // @Query("MATCH (m:Vote)<-[rating:RATED]-(user) WHERE id(m) = {movieId} RETURN rating")
    // List<Vote> getRatings(@Param("movieID") Long movieId);
    Vote findByVoter(Voter voter);
}
