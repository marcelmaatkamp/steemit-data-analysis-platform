package application.model;

import java.math.BigInteger;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import eu.bittrade.libs.steemj.base.models.Asset;
import eu.bittrade.libs.steemj.base.models.Authority;
import eu.bittrade.libs.steemj.base.models.TimePointSec;

@NodeEntity
public class Account {
    @GraphId Long id;
    public Account() { }
    public Account(String name) { 
        this.name = new AccountName();
        this.name.name = name;
    }

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

    
    
}