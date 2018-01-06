package application.model.steemj;

import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.Asset;
import eu.bittrade.libs.steemj.base.models.VoteState;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;
import java.util.List;

@NodeEntity
public class Discussion extends Comment {
    public String url;
    public String rootTitle;
    public Asset pendingPayoutValue;
    public Asset totalPendingPayoutValue;
    public List<VoteState> activeVotes;
    public List<String> replies;
    public long authorReputation;
    public Asset promoted;
    public String bodyLength;
    public List<AccountName> rebloggedBy;
    public AccountName firstRebloggedBy;
    public Date firstRebloggedOn;
    @GraphId Long id;

    public Discussion() { }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRootTitle() {
        return rootTitle;
    }

    public void setRootTitle(String rootTitle) {
        this.rootTitle = rootTitle;
    }

    public Asset getPendingPayoutValue() {
        return pendingPayoutValue;
    }

    public void setPendingPayoutValue(Asset pendingPayoutValue) {
        this.pendingPayoutValue = pendingPayoutValue;
    }

    public Asset getTotalPendingPayoutValue() {
        return totalPendingPayoutValue;
    }

    public void setTotalPendingPayoutValue(Asset totalPendingPayoutValue) {
        this.totalPendingPayoutValue = totalPendingPayoutValue;
    }

    public List<VoteState> getActiveVotes() {
        return activeVotes;
    }

    public void setActiveVotes(List<VoteState> activeVotes) {
        this.activeVotes = activeVotes;
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }

    public long getAuthorReputation() {
        return authorReputation;
    }

    public void setAuthorReputation(long authorReputation) {
        this.authorReputation = authorReputation;
    }

    public Asset getPromoted() {
        return promoted;
    }

    public void setPromoted(Asset promoted) {
        this.promoted = promoted;
    }

    public String getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(String bodyLength) {
        this.bodyLength = bodyLength;
    }

    public List<AccountName> getRebloggedBy() {
        return rebloggedBy;
    }

    public void setRebloggedBy(List<AccountName> rebloggedBy) {
        this.rebloggedBy = rebloggedBy;
    }

    public AccountName getFirstRebloggedBy() {
        return firstRebloggedBy;
    }

    public void setFirstRebloggedBy(AccountName firstRebloggedBy) {
        this.firstRebloggedBy = firstRebloggedBy;
    }

    public Date getFirstRebloggedOn() {
        return firstRebloggedOn;
    }

    public void setFirstRebloggedOn(Date firstRebloggedOn) {
        this.firstRebloggedOn = firstRebloggedOn;
    }
}