package application.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import application.serializers.VoteDeserializer;

// {"voter": "lauutorrez", "author": "blavin", "permlink": "seven-day-black-and-white-challenge-1st-entry", "weight": 1000}

@NodeEntity
// @JsonDeserialize(using = VoteDeserializer.class)
public class Vote {
	@GraphId private Long id;

    public String voter;
    public String author;
    public String permlink;
    public long weight;

	private Vote() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

    @JsonCreator
    public Vote(@JsonProperty("voter") String voter, @JsonProperty("author") String author, @JsonProperty("permlink") String permlink, @JsonProperty("weight") long weight){
        this.voter = voter;
        this.author = author;
        this.permlink = permlink;
        this.weight = weight;
    }
}