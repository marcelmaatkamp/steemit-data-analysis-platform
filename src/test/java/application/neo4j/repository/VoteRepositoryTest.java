package application.neo4j.repository;

import application.model.neo4j.Account;
import application.model.neo4j.Permlink;
import application.model.neo4j.Vote;
import application.repository.neo4j.AccountRepository;
import application.repository.neo4j.PermlinkRepository;
import application.repository.neo4j.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class VoteRepositoryTest {

    String AUTHOR_NAME = "TESTAUTHOR";
    String VOTER_NAME = "TESTVOTER";
    String PERMLINK_LINK = "http://TESTPERMLINK";
    int VOTE_WEIGHT = 100;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PermlinkRepository permlinkRepository;

    @Autowired
    private AccountRepository accountRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    // private Neo4jSession neo4jSession;
    Session session;


    @Autowired
    private ApplicationContext ctx;

    @Before
    public void clearAllGraphRepositories() {
        Map<String, GraphRepository> graphRepositories = ctx.getBeansOfType(GraphRepository.class);
        for (GraphRepository graphRepository : graphRepositories.values()) {
            graphRepository.deleteAll();
        }
    }

    @Test
    public void testCreateAccount() {
        Account author = new Account();
        author.setName(AUTHOR_NAME);

        Permlink permlink = new Permlink();
        permlink.setLink(PERMLINK_LINK);
        permlink = permlinkRepository.save(permlink);
        log.info(String.format("p(%s): %s ", permlink.getId(), permlinkRepository.findOne(permlink.getId())));

        author.posts.add(permlink);
        author = accountRepository.save(author);

        // assertThat(permlinkRepository.exists(permlink.getId())).isTrue();
        // Permlink samePermlink = permlinkRepository.findOne((long)permlink.getId().longValue());
        // assertThat(samePermlink).isNotNull();
        // assertThat(samePermlink.getId()).isEqualTo(permlink.getId());
        // assertThat(samePermlink.getLink()).isEqualTo(permlink.getLink());
    }

    @Test
    public void testPermlink() {
        long permlinkId = 6515;
        Permlink permlink = permlinkRepository.findOne(permlinkId);
        assertThat(permlink).isNotNull();
        assertThat(permlink.id).isNotNull().isEqualTo(permlinkId);
    }

    @Test
    public void testAccount() {
        long accountId = 6269;
        Account account = accountRepository.findOne(accountId);
        assertThat(account).isNotNull();
        assertThat(account.id).isNotNull().isEqualTo(accountId);
    }

    @Test
    // @UsingDataSet(locations = "star-trek-TNG-dataset.xml", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void testPersistence() {
        Account author = new Account();
        author.setName(AUTHOR_NAME);

        Permlink permlink = new Permlink();
        permlink.setLink(PERMLINK_LINK);
        // permlink = permlinkRepository.save(permlink);
        // log.info(String.format("p(%s): %s ", permlink.getId(), permlinkRepository.findOne(permlink.getId())));

        // assertThat(permlinkRepository.exists(permlink.getId())).isTrue();
        // Permlink samePermlink = permlinkRepository.findOne(permlink.getId().longValue());
        // assertThat(samePermlink).isNotNull();
        // assertThat(samePermlink.getId()).isEqualTo(permlink.getId());
        // assertThat(samePermlink.getLink()).isEqualTo(permlink.getLink());

        Account voter = new Account(VOTER_NAME);
        Vote vote = new Vote(voter,permlink,VOTE_WEIGHT);
        voter.votes.add(vote);
        // voter = accountRepository.save(voter);
        // assertThat(vote.getId()).isNotEqualTo(-1).isGreaterThan(0);
        vote = voteRepository.save(vote);

        Vote sameVote = voteRepository.findOne(vote.getId(),1);
        log.info(String.format("vote(%d): %s",vote.getId(),sameVote));

        assertThat(sameVote).isNotNull();
        assertThat(sameVote.voter).isNotNull();
        assertThat(sameVote.permlink).isNotNull();
        assertThat(sameVote.getWeight()).isEqualTo(VOTE_WEIGHT);
        assertThat(sameVote.getVoter().getName()).isEqualTo(VOTER_NAME);
        assertThat(sameVote.getPermlink().getLink()).isEqualTo(PERMLINK_LINK);
    }


}

