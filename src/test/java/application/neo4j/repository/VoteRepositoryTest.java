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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


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

    @Before
    public void setUp() {

    }

    @Test
    public void testPersistence() {
        Account author = new Account();
        author.setName(AUTHOR_NAME);

        Permlink permlink = new Permlink();
        permlink.setLink(PERMLINK_LINK);
        permlink.setAuthor(author);
        permlink = permlinkRepository.save(permlink);
        author.posts.add(permlink);
        author = accountRepository.save(author);

        Account otherAuthor = accountRepository.findOne(author.getId());
        assertThat(otherAuthor.getId()).isEqualTo(author.getId());
        assertThat(otherAuthor.getName()).isEqualTo(author.getName());

        Account voter = new Account();
        voter.setName(VOTER_NAME);

        Vote vote = new Vote();
        vote.permlink = permlink;
        vote.voter = voter;
        vote.weight = VOTE_WEIGHT;
        voter.votes.add(vote);
        voter = accountRepository.save(voter);

        Account otherVoter = accountRepository.findOne(voter.getId());
        assertThat(otherVoter.getId()).isEqualTo(voter.getId());
        assertThat(otherVoter.getName()).isEqualTo(voter.getName());

        vote = voteRepository.save(vote);

        Vote sameVote = voteRepository.findOne(vote.getId());
        log.info(String.format("vote(%s): %s", vote.getId(), sameVote));
        assertEquals(AUTHOR_NAME, sameVote.getPermlink().getAuthor().getName());
        assertEquals(VOTER_NAME, sameVote.getVoter().getName());
        assertEquals(PERMLINK_LINK, sameVote.getPermlink().getLink());
    }

}

