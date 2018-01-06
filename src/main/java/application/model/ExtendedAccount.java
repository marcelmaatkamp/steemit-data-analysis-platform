package application.model;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

// import eu.bittrade.libs.steemj.base.models.AppliedOperation;
// import eu.bittrade.libs.steemj.base.models.Asset;

@NodeEntity
public class ExtendedAccount extends Account {
    @GraphId Long id;

    public ExtendedAccount() { }
    public ExtendedAccount(String name) { 
        super(name);

    }

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
    
}