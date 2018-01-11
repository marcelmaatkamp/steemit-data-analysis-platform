package application.model.neo4j;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "VOTE")
public class Vote {
    @GraphId
    Long id;

    @StartNode
    public Voter voter;
    @EndNode
    public Permlink permlink;
    @Property
    public int weight;

    public Vote() {
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
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