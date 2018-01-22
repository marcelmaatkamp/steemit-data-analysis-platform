package application.model.neo4j;

import lombok.*;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@NoArgsConstructor
@ToString(of = {"id","name"})
public class Account {

    public Account(String name) {
        this.name = name;
    }

    @Getter
    public String name;

    @Getter
    @Relationship(type = "AUTHOR", direction = Relationship.UNDIRECTED)
    public Set<Permlink> posts = new HashSet<>();

    @Getter
    @Relationship(type = "VOTES", direction = Relationship.UNDIRECTED)
    public Set<Vote> votes = new HashSet<>();

    @Getter
    @GraphId
    Long id;

}
