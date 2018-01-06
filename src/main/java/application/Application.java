package application;

import application.model.VoteState;
import application.model.json.Vote;
import application.repository.DiscussionRepository;
import application.repository.ExtendedAccountRepository;
import application.repository.VoteRepository;
import application.repository.VoteStateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.apis.database.models.state.Discussion;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.ExtendedAccount;
import eu.bittrade.libs.steemj.base.models.Permlink;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
@EnableNeo4jRepositories
public class Application implements ApplicationRunner {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    ExtendedAccountRepository extendedAccountRepository;

    @Autowired
    VoteStateRepository voteStateRepository;

    @Autowired
    DiscussionRepository discussionRepository;

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
        // Vote vote = voteRepository.save(objectMapper.readValue(new String(message), Vote.class));
        Vote vote = objectMapper.readValue(new String(message), Vote.class);

        List<ExtendedAccount> extendedAccounts = steemJ.getAccounts(Arrays.asList(
                new AccountName(vote.author.name.name),
                new AccountName(vote.voter.name.name)));

        Discussion discussion = steemJ.getContent(new AccountName(vote.author.name.name), new Permlink(vote.permlink.getLink()));
        application.model.Discussion discussionNedo4j = new application.model.Discussion();
        BeanUtils.copyProperties(discussion, discussionNedo4j);
        discussionRepository.save(discussionNedo4j);

        ExtendedAccount extendedAuthorAccount = extendedAccounts.get(0);
        ExtendedAccount extendedVoterAccount = extendedAccounts.get(1);

        // application.model.ExtendedAccount extendedAuthorAccountNeo4j = new application.model.ExtendedAccount();
        // BeanUtils.copyProperties(extendedAuthorAccount, extendedAuthorAccountNeo4j);
        // log.info("extendedAuthorAccount: " + ReflectionToStringBuilder.toString(extendedAuthorAccount));
        // log.info("extendedAuthorAccountNeo4j: " + ReflectionToStringBuilder.toString(extendedAuthorAccountNeo4j));
        // extendedAuthorAccountNeo4j = extendedAccountRepository.save(extendedAuthorAccountNeo4j);
        // vote.author = extendedAuthorAccountNeo4j;

        application.model.ExtendedAccount extendedVoterAccountNeo4j = new application.model.ExtendedAccount();
        BeanUtils.copyProperties(extendedVoterAccount, extendedVoterAccountNeo4j);
        // log.info("extendedVoterAccount: " + ReflectionToStringBuilder.toString(extendedVoterAccount));
        // log.info("extendedVoterAccountNeo4j: " + ReflectionToStringBuilder.toString(extendedVoterAccountNeo4j));
        extendedVoterAccountNeo4j = extendedAccountRepository.save(extendedVoterAccountNeo4j);

        VoteState voteState = new VoteState();
        voteState.setVoter(vote.voter.name);

        voteState.setWeight(BigInteger.valueOf(vote.weight));
        voteState = voteStateRepository.save(voteState);
        // vote.voter = extendedVoterAccountNeo4j;
        // vote = voteRepository.save(objectMapper.readValue(new String(message), Vote.class));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(("HF version fetched: " + steemJ.getHardforkVersion()));
        log.info("Market ticker: " + steemJ.getTicker().toString());
        log.info("block: " + steemJ.getBlock(1));
    }

}
