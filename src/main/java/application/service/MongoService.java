package application.service;

import application.model.mongodb.AccountOperations.Vote;
import application.repository.mongodb.AccountOperationsRepository;
import com.mongodb.Mongo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
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

    @Autowired
    AccountOperationsRepository accountOperationsRepository;

    public List<Vote> getVotesBetween(LocalDateTime dayBeforeYesterday, LocalDateTime yesterday) {
        // yesterday.atZone(ZoneId.systemDefault()
        log.info(String.format("getting dates between dayBeforeYesterday(%t) and yesterday(%t)", dayBeforeYesterday, yesterday));

        List<Vote> votes =
                mongoTemplate.find(query(
                        where("type").is("vote").
                                and("timestamp").
                                gte(dayBeforeYesterday).
                                lt(yesterday)).
                                with(new Sort("timestamp", "-1")),
                        Vote.class);
        return votes;
    }

    public Page<Vote> listAllByPage(Pageable pageable) {
        log.info(String.format("getting dates for page %s", pageable));
        return accountOperationsRepository.listVotes(pageable);
    }
}
