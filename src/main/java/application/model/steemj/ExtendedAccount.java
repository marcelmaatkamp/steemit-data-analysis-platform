package application.model.steemj;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.tuple.Pair;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

// import eu.bittrade.libs.steemj.base.models.AppliedOperation;
// import eu.bittrade.libs.steemj.base.models.Asset;

@NodeEntity
public class ExtendedAccount extends Account {
    // public Asset vestingBalance;
    public long reputation;
    // public Map<BigInteger, AppliedOperation> transferHistory;
    // public Map<BigInteger, AppliedOperation> marketHistory;
    // public Map<BigInteger, AppliedOperation> postHistory;
    // public Map<BigInteger, AppliedOperation> voteHistory;
    // public Map<BigInteger, AppliedOperation> otherHistory;
    public List<AccountName> witnessVotes;
    public List<Pair<String, Long>> tagsUsage;
    public List<Pair<AccountName, Long>> guestBloggers;
    public List<String> openOrders;
    public List<String> comments;
    public List<String> blog;
    public List<String> feed;
    public List<String> recentReplies;
    public List<String> recommended;
    @GraphId
    Long id;

    public ExtendedAccount() {
    }

    public ExtendedAccount(String name) {
        super(name);

    }

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public List<AccountName> getWitnessVotes() {
        return witnessVotes;
    }

    public void setWitnessVotes(List<AccountName> witnessVotes) {
        this.witnessVotes = witnessVotes;
    }

    public List<Pair<String, Long>> getTagsUsage() {
        return tagsUsage;
    }

    public void setTagsUsage(List<Pair<String, Long>> tagsUsage) {
        this.tagsUsage = tagsUsage;
    }

    public List<Pair<AccountName, Long>> getGuestBloggers() {
        return guestBloggers;
    }

    public void setGuestBloggers(List<Pair<AccountName, Long>> guestBloggers) {
        this.guestBloggers = guestBloggers;
    }

    public List<String> getOpenOrders() {
        return openOrders;
    }

    public void setOpenOrders(List<String> openOrders) {
        this.openOrders = openOrders;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getBlog() {
        return blog;
    }

    public void setBlog(List<String> blog) {
        this.blog = blog;
    }

    public List<String> getFeed() {
        return feed;
    }

    public void setFeed(List<String> feed) {
        this.feed = feed;
    }

    public List<String> getRecentReplies() {
        return recentReplies;
    }

    public void setRecentReplies(List<String> recentReplies) {
        this.recentReplies = recentReplies;
    }

    public List<String> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<String> recommended) {
        this.recommended = recommended;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}