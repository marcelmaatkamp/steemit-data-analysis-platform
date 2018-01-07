package application.model.json;

import application.model.steemj.ExtendedAccount;
import application.model.steemj.Permlink;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;

// {"voter": "lauutorrez", "author": "blavin", "permlink": "seven-day-black-and-white-challenge-1st-entry", "weight": 1000}

public class Vote {

    public ExtendedAccount voter;
    public ExtendedAccount author;
    public Permlink permlink;
    public int weight;
    @GraphId
    Long id;

    public Vote() {
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}