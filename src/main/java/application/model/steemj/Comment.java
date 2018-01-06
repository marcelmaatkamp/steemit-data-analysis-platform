package application.model.steemj;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.math.BigInteger;

/**
 * Created by marcel on 06-01-18.
 */
@NodeEntity
public class Comment {
    public String category;
    public AccountName parentAuthor;
    public Permlink parentPermlink;
    public AccountName author;
    public Permlink permlink;
    public String title;
    public String body;
    public String jsonMetadata;
    public TimePointSec lastUpdate;
    public TimePointSec created;
    public TimePointSec active;
    public TimePointSec lastPayout;
    public short depth;
    public int children;
    public long netRshares;
    public long absRshares;
    public long voteRshares;
    public long childrenAbsRshares;
    public TimePointSec cashoutTime;
    public TimePointSec maxCashoutTime;
    public BigInteger totalVoteWeight;
    public long rewardWeight;
    public Asset totalPayoutValue;
    public Asset curatorPayoutValue;
    public long authorRewards;
    public int netVotes;
    public long rootComment;
    public Asset maxAcceptedPayout;
    public short percentSteemDollars;
    public boolean allowReplies;
    public boolean allowVotes;
    public boolean allowCurationRewards;
    public Object[] beneficiaries;
    @GraphId
    Long id;

    public Comment() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AccountName getParentAuthor() {
        return parentAuthor;
    }

    public void setParentAuthor(AccountName parentAuthor) {
        this.parentAuthor = parentAuthor;
    }

    public Permlink getParentPermlink() {
        return parentPermlink;
    }

    public void setParentPermlink(Permlink parentPermlink) {
        this.parentPermlink = parentPermlink;
    }

    public AccountName getAuthor() {
        return author;
    }

    public void setAuthor(AccountName author) {
        this.author = author;
    }

    public Permlink getPermlink() {
        return permlink;
    }

    public void setPermlink(Permlink permlink) {
        this.permlink = permlink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getJsonMetadata() {
        return jsonMetadata;
    }

    public void setJsonMetadata(String jsonMetadata) {
        this.jsonMetadata = jsonMetadata;
    }

    public TimePointSec getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(TimePointSec lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public TimePointSec getCreated() {
        return created;
    }

    public void setCreated(TimePointSec created) {
        this.created = created;
    }

    public TimePointSec getActive() {
        return active;
    }

    public void setActive(TimePointSec active) {
        this.active = active;
    }

    public TimePointSec getLastPayout() {
        return lastPayout;
    }

    public void setLastPayout(TimePointSec lastPayout) {
        this.lastPayout = lastPayout;
    }

    public short getDepth() {
        return depth;
    }

    public void setDepth(short depth) {
        this.depth = depth;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public long getNetRshares() {
        return netRshares;
    }

    public void setNetRshares(long netRshares) {
        this.netRshares = netRshares;
    }

    public long getAbsRshares() {
        return absRshares;
    }

    public void setAbsRshares(long absRshares) {
        this.absRshares = absRshares;
    }

    public long getVoteRshares() {
        return voteRshares;
    }

    public void setVoteRshares(long voteRshares) {
        this.voteRshares = voteRshares;
    }

    public long getChildrenAbsRshares() {
        return childrenAbsRshares;
    }

    public void setChildrenAbsRshares(long childrenAbsRshares) {
        this.childrenAbsRshares = childrenAbsRshares;
    }

    public TimePointSec getCashoutTime() {
        return cashoutTime;
    }

    public void setCashoutTime(TimePointSec cashoutTime) {
        this.cashoutTime = cashoutTime;
    }

    public TimePointSec getMaxCashoutTime() {
        return maxCashoutTime;
    }

    public void setMaxCashoutTime(TimePointSec maxCashoutTime) {
        this.maxCashoutTime = maxCashoutTime;
    }

    public BigInteger getTotalVoteWeight() {
        return totalVoteWeight;
    }

    public void setTotalVoteWeight(BigInteger totalVoteWeight) {
        this.totalVoteWeight = totalVoteWeight;
    }

    public long getRewardWeight() {
        return rewardWeight;
    }

    public void setRewardWeight(long rewardWeight) {
        this.rewardWeight = rewardWeight;
    }

    public Asset getTotalPayoutValue() {
        return totalPayoutValue;
    }

    public void setTotalPayoutValue(Asset totalPayoutValue) {
        this.totalPayoutValue = totalPayoutValue;
    }

    public Asset getCuratorPayoutValue() {
        return curatorPayoutValue;
    }

    public void setCuratorPayoutValue(Asset curatorPayoutValue) {
        this.curatorPayoutValue = curatorPayoutValue;
    }

    public long getAuthorRewards() {
        return authorRewards;
    }

    public void setAuthorRewards(long authorRewards) {
        this.authorRewards = authorRewards;
    }

    public int getNetVotes() {
        return netVotes;
    }

    public void setNetVotes(int netVotes) {
        this.netVotes = netVotes;
    }

    public long getRootComment() {
        return rootComment;
    }

    public void setRootComment(long rootComment) {
        this.rootComment = rootComment;
    }

    public Asset getMaxAcceptedPayout() {
        return maxAcceptedPayout;
    }

    public void setMaxAcceptedPayout(Asset maxAcceptedPayout) {
        this.maxAcceptedPayout = maxAcceptedPayout;
    }

    public short getPercentSteemDollars() {
        return percentSteemDollars;
    }

    public void setPercentSteemDollars(short percentSteemDollars) {
        this.percentSteemDollars = percentSteemDollars;
    }

    public boolean isAllowReplies() {
        return allowReplies;
    }

    public void setAllowReplies(boolean allowReplies) {
        this.allowReplies = allowReplies;
    }

    public boolean isAllowVotes() {
        return allowVotes;
    }

    public void setAllowVotes(boolean allowVotes) {
        this.allowVotes = allowVotes;
    }

    public boolean isAllowCurationRewards() {
        return allowCurationRewards;
    }

    public void setAllowCurationRewards(boolean allowCurationRewards) {
        this.allowCurationRewards = allowCurationRewards;
    }

    public Object[] getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Object[] beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

}
