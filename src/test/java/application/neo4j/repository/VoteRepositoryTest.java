package application.neo4j.repository;

import application.model.neo4j.Account;
import application.model.neo4j.Permlink;
import application.model.neo4j.Vote;
import application.repository.neo4j.VoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VoteRepositoryTest {

    String AUTHOR_NAME = "marcelmaatkamp";
    String VOTER_NAME = "r00sj3";
    String PERMLINK_LINK = "http://www.nu.nl";
    int VOTE_WEIGHT = 100;


    @Autowired
    private VoteRepository voteRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void testPersistence() {

        Permlink permlink = new Permlink();
        permlink.setLink(PERMLINK_LINK);

        Account author = new Account();
        author.setName(AUTHOR_NAME);
        author.posts.add(permlink);

        Account voter = new Account();
        voter.setName(VOTER_NAME);

        Vote vote = new Vote();
        vote.permlink = permlink;
        vote.voter = voter;
        vote.weight = VOTE_WEIGHT;

        voteRepository.save(vote);

        //then
        assertNotNull(vote.getId());

        Vote sameVote = voteRepository.findById(vote.getId());
        assertEquals(AUTHOR_NAME, sameVote.getPermlink().getAuthor().getName());
        assertEquals(VOTER_NAME, sameVote.getVoter().getName());
        assertEquals(PERMLINK_LINK, sameVote.getPermlink().getLink());
    }

}

