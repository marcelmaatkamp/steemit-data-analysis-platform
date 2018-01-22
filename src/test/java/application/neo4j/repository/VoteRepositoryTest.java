package application.neo4j.repository;

import application.model.neo4j.Account;
import application.model.neo4j.Permlink;
import application.model.neo4j.Vote;
import application.repository.neo4j.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class VoteRepositoryTest {

    String AUTHOR_NAME = "author";
    String VOTER_NAME = "voter";
    String PERMLINK_LINK = "http://www.nu.nl";
    int VOTE_WEIGHT = 100;


    @Autowired
    private VoteRepository voteRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void testPersistence() {
        Account author = new Account(AUTHOR_NAME);

        Permlink permlink = new Permlink(author, PERMLINK_LINK);
        author.posts.add(permlink);

        Account voter = new Account(VOTER_NAME);

        Vote vote = new Vote();
        vote.permlink = permlink;
        vote.voter = voter;
        vote.weight = VOTE_WEIGHT;
        voter.votes.add(vote);

        vote = voteRepository.save(vote);

        //then
        assertThat(vote.getId()).isNotEqualTo(-1).isGreaterThan(0);

        Vote sameVote = voteRepository.findOne(vote.getId(),1);
        log.info(String.format("vote(%d): %s",vote.getId(),sameVote));

        assertThat(sameVote).isNotNull();
        assertThat(sameVote.voter).isNotNull();
        assertThat(sameVote.permlink).isNotNull();
        assertThat(sameVote.getWeight()).isEqualTo(VOTE_WEIGHT);
        assertThat(sameVote.getVoter().getName()).isEqualTo(VOTER_NAME);
        assertThat(sameVote.getPermlink().getLink()).isEqualTo(PERMLINK_LINK);
        assertThat(sameVote.getPermlink().getAuthor().getName()).isEqualTo(AUTHOR_NAME);
    }

}

