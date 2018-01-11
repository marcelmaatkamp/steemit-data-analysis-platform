package application.model.neo4j;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Author extends Account {

    @Relationship(type = "AUTHOR")
    public Set<Permlink> posts;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
