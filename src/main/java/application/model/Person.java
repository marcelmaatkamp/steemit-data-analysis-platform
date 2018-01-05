package application.model;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Person {
    public Person() { }
    public Person(String name) {
        this.name = name;
    }
    public String name;
}