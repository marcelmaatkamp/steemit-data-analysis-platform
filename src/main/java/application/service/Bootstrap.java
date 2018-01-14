package application.service;

import application.model.mongodb.AccountOperations.Vote;
import application.model.neo4j.Author;
import application.model.neo4j.Permlink;
import application.model.neo4j.Voter;
import application.repository.neo4j.AuthorRepository;
import application.repository.neo4j.PermlinkRepository;
import application.repository.neo4j.VoteRepository;
import application.repository.neo4j.VoterRepository;
import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.ExtendedAccount;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class Bootstrap {

    @Autowired
    MongoService mongoService;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    SteemJ steemJ;

    @Autowired
    PermlinkRepository permlinkRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    VoterRepository voterRepository;

    private void save(Vote vote) throws SteemCommunicationException, SteemResponseException {
        List<ExtendedAccount> extendedAccounts = steemJ.getAccounts(Arrays.asList(
                new AccountName(vote.getAuthor()),
                new AccountName(vote.getVoter())));

        // author
        ExtendedAccount extendedAuthorAccount = extendedAccounts.get(0);
        ExtendedAccount extendedVoterAccount = extendedAccounts.get(1);

        // Lets insert into Neo4j
        Permlink permlink = permlinkRepository.findByLink(vote.getPermlink());
        if (permlink == null) {
            permlink = permlinkRepository.save(new Permlink(vote.getPermlink()));
        }

        Author author = authorRepository.findByName(extendedAuthorAccount.getName().getName());
        if (author == null) {
            author = new Author(extendedAuthorAccount.getName().getName());
            if (author.posts == null) {
                author.posts = new HashSet<>();
            }
            author.posts.add(permlink);
            author = authorRepository.save(author);
        }
        Voter voter = voterRepository.findByName(extendedVoterAccount.getName().getName());
        if (voter == null) {
            voter = new Voter(extendedVoterAccount.getName().getName());
            voter = voterRepository.save(voter);
        }

        application.model.neo4j.Vote voteByBVoter = new application.model.neo4j.Vote();
        voteByBVoter.permlink = permlink;
        voteByBVoter.voter = voter;
        voteByBVoter.weight = vote.getWeight();
        voteRepository.save(voteByBVoter);
    }


    public int votes(int days) throws SteemResponseException, SteemCommunicationException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeDays = now.minus(days, ChronoUnit.DAYS);
        List<Vote> votes = mongoService.getVotesBetween(beforeDays, now);

        for(Vote vote: votes) {
            save(vote);
        }

        return votes.size();
    }
}
