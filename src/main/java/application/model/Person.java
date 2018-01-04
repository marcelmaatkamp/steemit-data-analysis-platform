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
public class Person {
	@GraphId private Long id;

    public final String name;
    
	private Person() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

    @JsonCreator
    public Person(@JsonProperty("name") String name){
        this.name = name;
    }
}