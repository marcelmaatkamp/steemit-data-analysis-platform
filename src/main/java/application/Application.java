package application;

import application.model.database.Author;
import application.model.database.Permlink;
import application.model.database.Vote;
import application.model.database.Voter;
import application.repository.database.AuthorRepository;
import application.repository.database.PermlinkRepository;
import application.repository.database.VoteRepository;
import application.repository.database.VoterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.ExtendedAccount;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
@EnableNeo4jRepositories
public class Application implements ApplicationRunner {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    VoterRepository voterRepository;

    @Autowired
    PermlinkRepository permlinkRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SteemJ steemJ;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @RabbitListener(bindings =
        @QueueBinding(
            exchange = @Exchange(value = "${steemit.rabbitmq.exchange}", type = ExchangeTypes.TOPIC, durable = "true"),
            value = @Queue(value = "${steemit.rabbitmq.queue.votes}", durable = "true"),
            key = "steemit.vote"
        )
        // ,containerFactory="STATUS_LISTENER_CONTAINER_FACTORY"
    )
    public void process(byte[] message) throws IOException, SteemCommunicationException, SteemResponseException {
        application.model.json.Vote vote = objectMapper.readValue(new String(message), application.model.json.Vote.class);
        log.info("vote: " + vote.toString());

        List<ExtendedAccount> extendedAccounts = steemJ.getAccounts(Arrays.asList(
                new AccountName(vote.author.name.name),
                new AccountName(vote.voter.name.name)));

        // author
        ExtendedAccount extendedAuthorAccount = extendedAccounts.get(0);
        ExtendedAccount extendedVoterAccount = extendedAccounts.get(1);

        // Lets insert into Neo4j
        Author author = authorRepository.findByName(extendedAuthorAccount.getName().getName());
        if (author == null) {
            author = new Author(extendedAuthorAccount.getName().getName());
        }
        Voter voter = voterRepository.findByName(extendedVoterAccount.getName().getName());
        if (voter == null) {
            voter = new Voter(extendedVoterAccount.getName().getName());
        }
        Permlink permlink = permlinkRepository.findByLink(vote.permlink.getLink());
        if (permlink == null) {
            permlink = permlinkRepository.save(new Permlink(vote.permlink.getLink()));
        }
        Vote voteByBVoter = new Vote();
        voteByBVoter.permlink = permlink;
        voteByBVoter.voter = voter;
        voteByBVoter.weight = vote.weight;
        voteRepository.save(voteByBVoter);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(("HF version fetched: " + steemJ.getHardforkVersion()));
        log.info("Market ticker: " + steemJ.getTicker().toString());
        log.info("block: " + steemJ.getBlock(1));
    }

}
