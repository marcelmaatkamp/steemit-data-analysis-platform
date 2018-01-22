package application.model.neo4j;

import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
=======
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
>>>>>>> 4f08b00f9df500180f621ffb1bff8b14b456b486
import org.neo4j.ogm.annotation.*;

@Data
@NoArgsConstructor
@RelationshipEntity(type = "VOTE")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Vote {

    @NonNull
    @StartNode
    public Account voter;

    @NonNull
    @EndNode
    public Permlink permlink;

    @NonNull
    @Property
    public int weight;

    @GraphId
    public Long id;
}
