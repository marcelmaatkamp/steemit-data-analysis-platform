package application.model.neo4j;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@NoArgsConstructor
@ToString(of = {"id","link"})
public class Permlink {

    public Permlink(Account author, String link) {
        this.author = author;
        this.link = link;
    }

    @Getter
    public String link;

    @Getter
    @GraphId
    Long id;

    @Getter
    @Relationship(type = "AUTHOR", direction = Relationship.OUTGOING)
    Account author;

}
