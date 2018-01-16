package application.model.neo4j;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Account {

    @GraphId
    Long id;

    public String name;

    @Relationship(type = "AUTHOR", direction=Relationship.UNDIRECTED)
    public Set<Permlink> posts = new HashSet<>();

    @Relationship(type = "VOTES", direction=Relationship.UNDIRECTED)
    public Set<Vote> votes = new HashSet<>();

    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permlink> getPosts() {
        return posts;
    }

    public void setPosts(Set<Permlink> posts) {
        this.posts = posts;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
