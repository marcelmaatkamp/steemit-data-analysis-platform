package application.model.database;

import application.model.steemj.ExtendedAccount;
import application.model.steemj.Permlink;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "VOTE")
public class VoteRelation {
    @StartNode
    public ExtendedAccount voter;
    public ExtendedAccount author;
    @EndNode
    public Permlink permlink;
    public int weight;
    @GraphId
    Long id;

    public VoteRelation() {
    }

    public ExtendedAccount getVoter() {
        return voter;
    }

    public void setVoter(ExtendedAccount voter) {
        this.voter = voter;
    }

    public ExtendedAccount getAuthor() {
        return author;
    }

    public void setAuthor(ExtendedAccount author) {
        this.author = author;
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
}