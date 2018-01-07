package application.model.steemj;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

// {"voter": "lauutorrez", "author": "blavin", "permlink": "seven-day-black-and-white-challenge-1st-entry", "weight": 1000}

@NodeEntity
public class PublicKey {

    // private ECKey publicKey;
    public String prefix;
    @GraphId
    Long id;

    public PublicKey() {
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}