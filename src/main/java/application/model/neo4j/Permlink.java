package application.model.neo4j;

<<<<<<< HEAD
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
=======
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
>>>>>>> 4f08b00f9df500180f621ffb1bff8b14b456b486
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Permlink {

    @GraphId
    public Long id;

    @NonNull
    public String link;

}
