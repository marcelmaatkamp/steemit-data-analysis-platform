package application.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@NodeEntity
public class Vote {
	@GraphId private Long id;

    public final String voter;
    public final String author;
    public final String permlink;
    public final long weight;

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