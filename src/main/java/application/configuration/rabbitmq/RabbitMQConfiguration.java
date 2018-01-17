package application.configuration.rabbitmq;

import application.model.neo4j.Account;
import application.model.neo4j.Permlink;
import application.model.neo4j.Vote;
import application.repository.neo4j.AccountRepository;
import application.repository.neo4j.PermlinkRepository;
import application.repository.neo4j.VoteRepository;
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
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@Slf4j
public class RabbitMQConfiguration { 

    @Autowired
    org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory;

    @Autowired
    PermlinkRepository permlinkRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    SteemJ steemJ;

    // @Bean(name="STATUS_LISTENER_CONTAINER_FACTORY")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(1);
        return factory;
    }

    @RabbitListener(bindings =
    @QueueBinding(
            exchange = @Exchange(value = "${steemit.rabbitmq.exchange}", type = ExchangeTypes.TOPIC, durable = "false"),
            value = @Queue(value = "${steemit.rabbitmq.queue.votes}", durable = "true"),
            key = "steemit.vote"
    )
            // ,containerFactory="STATUS_LISTENER_CONTAINER_FACTORY"
    )
    public void process(byte[] message) throws IOException, SteemCommunicationException, SteemResponseException {
        application.model.json.Vote vote = objectMapper.readValue(new String(message), application.model.json.Vote.class);
        log.info("vote: " + vote.toString());

        save(vote);


    }

    private void save(application.model.json.Vote vote) throws SteemCommunicationException, SteemResponseException {
        List<ExtendedAccount> extendedAccounts = steemJ.getAccounts(Arrays.asList(
                new AccountName(vote.author.name.name),
                new AccountName(vote.voter.name.name)));

        // author
        ExtendedAccount extendedAuthorAccount = extendedAccounts.get(0);
        ExtendedAccount extendedVoterAccount = extendedAccounts.get(1);

        // Lets insert into Neo4j
        Permlink permlink = permlinkRepository.findByLink(vote.permlink.getLink());
        if (permlink == null) {
            permlink = permlinkRepository.save(new Permlink(vote.permlink.getLink()));
        }

        Account author = accountRepository.findByName(extendedAuthorAccount.getName().getName());
        if (author == null) {
            author = new Account(extendedAuthorAccount.getName().getName());
            author.posts.add(permlink);
            author = accountRepository.save(author);
        }
        Account voter = accountRepository.findByName(extendedVoterAccount.getName().getName());
        if (voter == null) {
            voter = new Account(extendedVoterAccount.getName().getName());
            voter = accountRepository.save(voter);
        }

        Vote voteByBVoter = new Vote();
        voteByBVoter.permlink = permlink;
        voteByBVoter.voter = voter;
        voteByBVoter.weight = vote.weight;
        voteRepository.save(voteByBVoter);
    }


}