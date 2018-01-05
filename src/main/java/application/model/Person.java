package application.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Person {
    @GraphId Long id;

    public Person() { }
    public Person(String name) {
        this.name = name;
    }
    public String name;
}