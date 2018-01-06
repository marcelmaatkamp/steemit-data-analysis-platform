package application.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

// {"voter": "lauutorrez", "author": "blavin", "permlink": "seven-day-black-and-white-challenge-1st-entry", "weight": 1000}

@NodeEntity
public class PublicKey {

    @GraphId Long id;
    public PublicKey() { }

    // private ECKey publicKey;
    public String prefix;
    
}