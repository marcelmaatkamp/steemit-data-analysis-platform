package application.model.steemj;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

// {"voter": "lauutorrez", "author": "blavin", "permlink": "seven-day-black-and-white-challenge-1st-entry", "weight": 1000}

/**
 * 
 */

@NodeEntity
public class AccountName {
    @GraphId Long id;
    public AccountName() { }
    public String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}