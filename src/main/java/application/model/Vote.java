package application.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

// {"voter": "lauutorrez", "author": "blavin", "permlink": "seven-day-black-and-white-challenge-1st-entry", "weight": 1000}

@NodeEntity
public class Vote {
    @GraphId
    private long id;
    
    public Vote() { }
    public Person voter;
    public Person author;
    public String permlink;
    public int weight;
}