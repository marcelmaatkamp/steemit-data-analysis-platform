package application.service;

import application.model.mongodb.AccountOperations.Vote;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

@Service
public class MongoService {

    @Autowired
    Mongo mongo;

    @Autowired
    MongoTemplate mongoTemplate;

    enum SteemDataCollections {
        ACCOUNTOPERATIONS("ACCOUNTOPERATIONS"),
        ACCOUNTS("ACCOUNTS");

        String collection;
        SteemDataCollections(String collection) {
            this.collection  = collection;
        }
        String getCollection() {
            return collection;
        }
    }

    public List<Vote> getVotes(Calendar calendar) {
        List<Vote> votes = mongoTemplate.find(query(where("type").is("vote").and("timestamp").gte(calendar)), Vote.class);
        return votes;
    }
}
