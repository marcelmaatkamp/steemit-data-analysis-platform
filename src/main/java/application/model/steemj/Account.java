package application.model.steemj;

import eu.bittrade.libs.steemj.base.models.Asset;
import eu.bittrade.libs.steemj.base.models.Authority;
import eu.bittrade.libs.steemj.base.models.TimePointSec;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.math.BigInteger;
import java.util.List;

@NodeEntity
public class Account {
    public AccountName name;
    public Authority owner;
    public Authority active;
    public Authority posting;
    public PublicKey memoKey;
    public String jsonMetadata;
    public AccountName proxy;
    public TimePointSec lastOwnerUpdate;
    public TimePointSec lastAccountUpdate;
    public TimePointSec created;
    public boolean mined;
    public boolean ownerChallenged;
    public boolean activeChallenged;
    public TimePointSec lastOwnerProved;
    public TimePointSec lastActiveProved;
    public AccountName recoveryAccount;
    public AccountName resetAccount;
    public TimePointSec lastAccountRecovery;
    public long commentCount;
    public long lifetimeVoteCount;
    public long postCount;
    public boolean canVote;
    public int votingPower;
    public TimePointSec lastVoteTime;
    public Asset balance;
    public Asset savingsBalance;
    public Asset sbdBalance;
    public BigInteger sbdSeconds;
    public TimePointSec sbdSecondsLastUpdate;
    public TimePointSec sbdLastInterestPayment;
    public Asset savingsSbdBalance;
    public BigInteger savingsSbdSeconds;
    public TimePointSec savingsSbdSecondsLastUpdate;
    public TimePointSec savingsSbdLastInterestPayment;
    public short savingWithdrawRequests;
    public Asset rewardSdbBalance;
    public Asset rewardSteemBalance;
    public Asset rewardVestingBalance;
    public Asset rewardVestingSteem;
    public long curationRewards;
    public long postingRewards;
    public Asset vestingShares;
    public Asset delegatedVestingShares;
    public Asset receivedVestingShares;
    public Asset vestingWithdrawRate;
    public TimePointSec nextVestingWithdrawal;
    public long withdrwan;
    public long toWithdraw;
    public int withdrawRoutes;
    public List<Long> proxiedVsfVotes;
    public int witnessesVotedFor;
    public long averageBandwidth;
    public long lifetimeBandwidth;
    public TimePointSec lastBandwidthUpdate;
    public long averageMarketBandwidth;
    public long lifetimeMarketBandwidth;
    public TimePointSec lastMarketBandwidthUpdate;
    public TimePointSec lastPost;
    public TimePointSec lastRootPost;
    @GraphId
    Long id;

    public Account() {
    }

    public Account(String name) {
        this.name = new AccountName();
        this.name.name = name;
    }

    public Authority getActive() {
        return active;
    }

    public void setActive(Authority active) {
        this.active = active;
    }

    public Authority getPosting() {
        return posting;
    }

    public void setPosting(Authority posting) {
        this.posting = posting;
    }

    public PublicKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(PublicKey memoKey) {
        this.memoKey = memoKey;
    }

    public String getJsonMetadata() {
        return jsonMetadata;
    }

    public void setJsonMetadata(String jsonMetadata) {
        this.jsonMetadata = jsonMetadata;
    }

    public AccountName getProxy() {
        return proxy;
    }

    public void setProxy(AccountName proxy) {
        this.proxy = proxy;
    }

    public TimePointSec getLastOwnerUpdate() {
        return lastOwnerUpdate;
    }

    public void setLastOwnerUpdate(TimePointSec lastOwnerUpdate) {
        this.lastOwnerUpdate = lastOwnerUpdate;
    }

    public TimePointSec getLastAccountUpdate() {
        return lastAccountUpdate;
    }

    public void setLastAccountUpdate(TimePointSec lastAccountUpdate) {
        this.lastAccountUpdate = lastAccountUpdate;
    }

    public TimePointSec getCreated() {
        return created;
    }

    public void setCreated(TimePointSec created) {
        this.created = created;
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public boolean isOwnerChallenged() {
        return ownerChallenged;
    }

    public void setOwnerChallenged(boolean ownerChallenged) {
        this.ownerChallenged = ownerChallenged;
    }

    public boolean isActiveChallenged() {
        return activeChallenged;
    }

    public void setActiveChallenged(boolean activeChallenged) {
        this.activeChallenged = activeChallenged;
    }

    public TimePointSec getLastOwnerProved() {
        return lastOwnerProved;
    }

    public void setLastOwnerProved(TimePointSec lastOwnerProved) {
        this.lastOwnerProved = lastOwnerProved;
    }

    public TimePointSec getLastActiveProved() {
        return lastActiveProved;
    }

    public void setLastActiveProved(TimePointSec lastActiveProved) {
        this.lastActiveProved = lastActiveProved;
    }

    public AccountName getRecoveryAccount() {
        return recoveryAccount;
    }

    public void setRecoveryAccount(AccountName recoveryAccount) {
        this.recoveryAccount = recoveryAccount;
    }

    public AccountName getResetAccount() {
        return resetAccount;
    }

    public void setResetAccount(AccountName resetAccount) {
        this.resetAccount = resetAccount;
    }

    public TimePointSec getLastAccountRecovery() {
        return lastAccountRecovery;
    }

    public void setLastAccountRecovery(TimePointSec lastAccountRecovery) {
        this.lastAccountRecovery = lastAccountRecovery;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public long getLifetimeVoteCount() {
        return lifetimeVoteCount;
    }

    public void setLifetimeVoteCount(long lifetimeVoteCount) {
        this.lifetimeVoteCount = lifetimeVoteCount;
    }

    public long getPostCount() {
        return postCount;
    }

    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }

    public boolean isCanVote() {
        return canVote;
    }

    public void setCanVote(boolean canVote) {
        this.canVote = canVote;
    }

    public int getVotingPower() {
        return votingPower;
    }

    public void setVotingPower(int votingPower) {
        this.votingPower = votingPower;
    }

    public TimePointSec getLastVoteTime() {
        return lastVoteTime;
    }

    public void setLastVoteTime(TimePointSec lastVoteTime) {
        this.lastVoteTime = lastVoteTime;
    }

    public Asset getBalance() {
        return balance;
    }

    public void setBalance(Asset balance) {
        this.balance = balance;
    }

    public Asset getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(Asset savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public Asset getSbdBalance() {
        return sbdBalance;
    }

    public void setSbdBalance(Asset sbdBalance) {
        this.sbdBalance = sbdBalance;
    }

    public BigInteger getSbdSeconds() {
        return sbdSeconds;
    }

    public void setSbdSeconds(BigInteger sbdSeconds) {
        this.sbdSeconds = sbdSeconds;
    }

    public TimePointSec getSbdSecondsLastUpdate() {
        return sbdSecondsLastUpdate;
    }

    public void setSbdSecondsLastUpdate(TimePointSec sbdSecondsLastUpdate) {
        this.sbdSecondsLastUpdate = sbdSecondsLastUpdate;
    }

    public TimePointSec getSbdLastInterestPayment() {
        return sbdLastInterestPayment;
    }

    public void setSbdLastInterestPayment(TimePointSec sbdLastInterestPayment) {
        this.sbdLastInterestPayment = sbdLastInterestPayment;
    }

    public Asset getSavingsSbdBalance() {
        return savingsSbdBalance;
    }

    public void setSavingsSbdBalance(Asset savingsSbdBalance) {
        this.savingsSbdBalance = savingsSbdBalance;
    }

    public BigInteger getSavingsSbdSeconds() {
        return savingsSbdSeconds;
    }

    public void setSavingsSbdSeconds(BigInteger savingsSbdSeconds) {
        this.savingsSbdSeconds = savingsSbdSeconds;
    }

    public TimePointSec getSavingsSbdSecondsLastUpdate() {
        return savingsSbdSecondsLastUpdate;
    }

    public void setSavingsSbdSecondsLastUpdate(TimePointSec savingsSbdSecondsLastUpdate) {
        this.savingsSbdSecondsLastUpdate = savingsSbdSecondsLastUpdate;
    }

    public TimePointSec getSavingsSbdLastInterestPayment() {
        return savingsSbdLastInterestPayment;
    }

    public void setSavingsSbdLastInterestPayment(TimePointSec savingsSbdLastInterestPayment) {
        this.savingsSbdLastInterestPayment = savingsSbdLastInterestPayment;
    }

    public short getSavingWithdrawRequests() {
        return savingWithdrawRequests;
    }

    public void setSavingWithdrawRequests(short savingWithdrawRequests) {
        this.savingWithdrawRequests = savingWithdrawRequests;
    }

    public Asset getRewardSdbBalance() {
        return rewardSdbBalance;
    }

    public void setRewardSdbBalance(Asset rewardSdbBalance) {
        this.rewardSdbBalance = rewardSdbBalance;
    }

    public Asset getRewardSteemBalance() {
        return rewardSteemBalance;
    }

    public void setRewardSteemBalance(Asset rewardSteemBalance) {
        this.rewardSteemBalance = rewardSteemBalance;
    }

    public Asset getRewardVestingBalance() {
        return rewardVestingBalance;
    }

    public void setRewardVestingBalance(Asset rewardVestingBalance) {
        this.rewardVestingBalance = rewardVestingBalance;
    }

    public Asset getRewardVestingSteem() {
        return rewardVestingSteem;
    }

    public void setRewardVestingSteem(Asset rewardVestingSteem) {
        this.rewardVestingSteem = rewardVestingSteem;
    }

    public long getCurationRewards() {
        return curationRewards;
    }

    public void setCurationRewards(long curationRewards) {
        this.curationRewards = curationRewards;
    }

    public long getPostingRewards() {
        return postingRewards;
    }

    public void setPostingRewards(long postingRewards) {
        this.postingRewards = postingRewards;
    }

    public Asset getVestingShares() {
        return vestingShares;
    }

    public void setVestingShares(Asset vestingShares) {
        this.vestingShares = vestingShares;
    }

    public Asset getDelegatedVestingShares() {
        return delegatedVestingShares;
    }

    public void setDelegatedVestingShares(Asset delegatedVestingShares) {
        this.delegatedVestingShares = delegatedVestingShares;
    }

    public Asset getReceivedVestingShares() {
        return receivedVestingShares;
    }

    public void setReceivedVestingShares(Asset receivedVestingShares) {
        this.receivedVestingShares = receivedVestingShares;
    }

    public Asset getVestingWithdrawRate() {
        return vestingWithdrawRate;
    }

    public void setVestingWithdrawRate(Asset vestingWithdrawRate) {
        this.vestingWithdrawRate = vestingWithdrawRate;
    }

    public TimePointSec getNextVestingWithdrawal() {
        return nextVestingWithdrawal;
    }

    public void setNextVestingWithdrawal(TimePointSec nextVestingWithdrawal) {
        this.nextVestingWithdrawal = nextVestingWithdrawal;
    }

    public long getWithdrwan() {
        return withdrwan;
    }

    public void setWithdrwan(long withdrwan) {
        this.withdrwan = withdrwan;
    }

    public long getToWithdraw() {
        return toWithdraw;
    }

    public void setToWithdraw(long toWithdraw) {
        this.toWithdraw = toWithdraw;
    }

    public int getWithdrawRoutes() {
        return withdrawRoutes;
    }

    public void setWithdrawRoutes(int withdrawRoutes) {
        this.withdrawRoutes = withdrawRoutes;
    }

    public List<Long> getProxiedVsfVotes() {
        return proxiedVsfVotes;
    }

    public void setProxiedVsfVotes(List<Long> proxiedVsfVotes) {
        this.proxiedVsfVotes = proxiedVsfVotes;
    }

    public int getWitnessesVotedFor() {
        return witnessesVotedFor;
    }

    public void setWitnessesVotedFor(int witnessesVotedFor) {
        this.witnessesVotedFor = witnessesVotedFor;
    }

    public long getAverageBandwidth() {
        return averageBandwidth;
    }

    public void setAverageBandwidth(long averageBandwidth) {
        this.averageBandwidth = averageBandwidth;
    }

    public long getLifetimeBandwidth() {
        return lifetimeBandwidth;
    }

    public void setLifetimeBandwidth(long lifetimeBandwidth) {
        this.lifetimeBandwidth = lifetimeBandwidth;
    }

    public TimePointSec getLastBandwidthUpdate() {
        return lastBandwidthUpdate;
    }

    public void setLastBandwidthUpdate(TimePointSec lastBandwidthUpdate) {
        this.lastBandwidthUpdate = lastBandwidthUpdate;
    }

    public long getAverageMarketBandwidth() {
        return averageMarketBandwidth;
    }

    public void setAverageMarketBandwidth(long averageMarketBandwidth) {
        this.averageMarketBandwidth = averageMarketBandwidth;
    }

    public long getLifetimeMarketBandwidth() {
        return lifetimeMarketBandwidth;
    }

    public void setLifetimeMarketBandwidth(long lifetimeMarketBandwidth) {
        this.lifetimeMarketBandwidth = lifetimeMarketBandwidth;
    }

    public TimePointSec getLastMarketBandwidthUpdate() {
        return lastMarketBandwidthUpdate;
    }

    public void setLastMarketBandwidthUpdate(TimePointSec lastMarketBandwidthUpdate) {
        this.lastMarketBandwidthUpdate = lastMarketBandwidthUpdate;
    }

    public TimePointSec getLastPost() {
        return lastPost;
    }

    public void setLastPost(TimePointSec lastPost) {
        this.lastPost = lastPost;
    }

    public TimePointSec getLastRootPost() {
        return lastRootPost;
    }

    public void setLastRootPost(TimePointSec lastRootPost) {
        this.lastRootPost = lastRootPost;
    }


    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}