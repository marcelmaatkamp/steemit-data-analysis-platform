package application.model.neo4j;

import lombok.*;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"posts", "votes"})
@ToString(exclude = {"posts", "votes"})
public class Account {
    @NonNull
    public String name;
    @Relationship(type = "AUTHOR", direction = Relationship.UNDIRECTED)
    public Set<Permlink> posts = new HashSet<>();
    @Relationship(type = "VOTES", direction = Relationship.UNDIRECTED)
    public Set<Vote> votes = new HashSet<>();
    @GraphId
    public Long id;
}
