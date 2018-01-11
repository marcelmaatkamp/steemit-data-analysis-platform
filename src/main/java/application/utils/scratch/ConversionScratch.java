package application.utils.scratch;

import application.repository.neo4j.PermlinkRepository;
import application.repository.neo4j.VoteRepository;
import application.repository.steemj.DiscussionRepository;
import application.repository.steemj.ExtendedAccountRepository;
import application.repository.steemj.VoteStateRepository;
import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ConversionScratch {

    @Autowired
    SteemJ steemJ;

    @Autowired
    ExtendedAccountRepository extendedAccountRepository;

    @Autowired
    VoteStateRepository voteStateRepository;

    @Autowired
    DiscussionRepository discussionRepository;

    @Autowired
    PermlinkRepository permlinkRepository;

    @Autowired
    VoteRepository voteRelationRepository;

    public void convert() throws SteemResponseException, SteemCommunicationException {
        String authorName = "author";
        String voterName = "voter";

        List<eu.bittrade.libs.steemj.base.models.ExtendedAccount> extendedAccounts = steemJ.getAccounts(Arrays.asList(
                new AccountName(authorName),
                new AccountName(voterName)));

        eu.bittrade.libs.steemj.base.models.ExtendedAccount extendedAuthorAccount = extendedAccounts.get(0);
        application.model.steemj.ExtendedAccount extendedAuthorAccountNeo4j = extendedAccountRepository.findByName(extendedAuthorAccount.getName().getName());
        if (extendedAuthorAccountNeo4j == null) {
            extendedAuthorAccountNeo4j = new application.model.steemj.ExtendedAccount();
            BeanUtils.copyProperties(extendedAuthorAccount, extendedAuthorAccountNeo4j);
            extendedAuthorAccountNeo4j = extendedAccountRepository.save(extendedAuthorAccountNeo4j);
        }


        eu.bittrade.libs.steemj.base.models.ExtendedAccount extendedVoterAccount = extendedAccounts.get(1);
        application.model.steemj.ExtendedAccount extendedVoterAccountNeo4j = extendedAccountRepository.findByName(extendedVoterAccount.getName().getName());
        if (extendedVoterAccountNeo4j == null) {
            extendedVoterAccountNeo4j = new application.model.steemj.ExtendedAccount();
            BeanUtils.copyProperties(extendedVoterAccount, extendedVoterAccountNeo4j);
            extendedVoterAccountNeo4j = extendedAccountRepository.save(extendedVoterAccountNeo4j);
        }
    }
}
