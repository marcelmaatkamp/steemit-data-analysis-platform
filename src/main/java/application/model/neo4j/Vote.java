package application.model.neo4j;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@Data
@NoArgsConstructor
@RelationshipEntity(type = "VOTE")
public class Vote {

    @GraphId
    private Long id;

    @Property
    public int weight;

    @StartNode
    public Account voter;

    @EndNode
    public Permlink permlink;

}