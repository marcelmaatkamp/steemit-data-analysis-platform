package application.service;

import application.model.mongodb.AccountOperations.Vote;
import application.repository.neo4j.VoteRepository;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Bootstrap {

    @Autowired
    MongoService mongoService;

    @Autowired
    VoteRepository voteRepository;


    public void votes(int days) {

        DateTime end = new DateTime();
        DateTime start = new DateTime().minusDays(days);
        Period period = new Period(start, end);
        DateTime calc = start.plus(period);

        List<Vote> votes = new ArrayList<>(); // mongoService.getVotes(calc.toGregorianCalendar());
        for(Vote vote: votes) {
            application.model.neo4j.Vote voteNeo4j = new application.model.neo4j.Vote();
            BeanUtils.copyProperties(vote, voteNeo4j);
            voteRepository.save(voteNeo4j);
        }

    }
}
