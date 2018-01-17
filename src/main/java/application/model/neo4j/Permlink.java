package application.model.neo4j;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Permlink {

    @GraphId
    public Long id;

    @NonNull
    public String link;


    @NonNull
    @Relationship(type = "AUTHOR", direction = Relationship.OUTGOING)
    public Account author;
}
