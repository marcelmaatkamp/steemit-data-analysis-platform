package application.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Person {
	@GraphId private Long id;

    public String name;
    
	private Person() {
		// Empty constructor required as of Neo4j API 2.0.5
	};

    @JsonCreator
    public Person(@JsonProperty("name") String name){
        this.name = name;
    }
}