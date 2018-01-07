package application.model.steemj;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.math.BigInteger;

/**
 * Created by marcel on 06-01-18.
 */
@NodeEntity
public class VoteState {
    public AccountName voter;
    public BigInteger weight;
    public BigInteger rshares;
    public int percent;
    public BigInteger reputation;
    public TimePointSec time;
    @GraphId
    Long id;

    public VoteState() {
    }

    public AccountName getVoter() {
        return voter;
    }

    public void setVoter(AccountName voter) {
        this.voter = voter;
    }

    public BigInteger getWeight() {
        return weight;
    }

    public void setWeight(BigInteger weight) {
        this.weight = weight;
    }

    public BigInteger getRshares() {
        return rshares;
    }

    public void setRshares(BigInteger rshares) {
        this.rshares = rshares;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public BigInteger getReputation() {
        return reputation;
    }

    public void setReputation(BigInteger reputation) {
        this.reputation = reputation;
    }

    public TimePointSec getTime() {
        return time;
    }

    public void setTime(TimePointSec time) {
        this.time = time;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
