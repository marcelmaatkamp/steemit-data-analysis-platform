package application.model.neo4j;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "VOTE")
public class Vote {
    @StartNode
    public Account voter;
    @EndNode
    public Permlink permlink;
    @Property
    public int weight;
    @GraphId
    Long id;
    public Vote() {
    }

    public Account getVoter() {
        return voter;
    }

    public void setVoter(Account voter) {
        this.voter = voter;
    }

    public Permlink getPermlink() {
        return permlink;
    }

    public void setPermlink(Permlink permlink) {
        this.permlink = permlink;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}