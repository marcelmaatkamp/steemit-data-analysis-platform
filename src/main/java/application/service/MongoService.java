package application.service;

import application.model.mongodb.AccountOperations.Vote;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class MongoService {

    @Autowired
    Mongo mongo;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Vote> getVotesBetween(Calendar start, Calendar end) {
        List<Vote> votes =
                mongoTemplate.find(query(
                        where("type").is("vote").
                                and("timestamp").
                                gte(start.getTime()).
                                lt(end.getTime())).
                        with(new Sort("timestamp", "-1")).
                        limit(10), Vote.class);
        return votes;
    }

    public List<Vote> getVotesBetween(LocalDateTime dayBeforeYesterday, LocalDateTime yesterday) {
        // yesterday.atZone(ZoneId.systemDefault()
        List<Vote> votes =
                mongoTemplate.find(query(
                        where("type").is("vote").
                                and("timestamp").
                                gte(dayBeforeYesterday).
                                lt(yesterday)).
                        with(new Sort("timestamp", "-1")).
                        limit(10), Vote.class);
        return votes;
    }
}
