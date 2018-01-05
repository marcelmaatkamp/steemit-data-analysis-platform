package application.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class ExtendedAccount {
    @GraphId Long id;

    public ExtendedAccount() { }
    public ExtendedAccount(String name) {
        this.name = name;
    }
    public String name;
}