package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

import application.model.Vote;
import application.repository.ExtendedAccountRepository;
import application.repository.VoteRepository;
import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.apis.database.models.state.Discussion;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.ExtendedAccount;
import eu.bittrade.libs.steemj.base.models.Permlink;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableNeo4jRepositories
public class Application implements ApplicationRunner {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    ExtendedAccountRepository extendedAccountRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SteemJ steemJ;

    @RabbitListener(bindings =
        @QueueBinding(
            exchange = @Exchange(value = "${steemit.rabbitmq.exchange}", type = ExchangeTypes.TOPIC, durable = "true"),
            value = @Queue(value = "${steemit.rabbitmq.queue.votes}", durable = "true"),
            key = "steemit.vote"
        )
        // ,containerFactory="STATUS_LISTENER_CONTAINER_FACTORY"
    )
    public void process(byte[] message) throws JsonParseException, JsonMappingException, IOException, SteemCommunicationException, SteemResponseException {
        Vote vote = voteRepository.save(objectMapper.readValue(new String(message), Vote.class));
        List<ExtendedAccount> extendedAccounts = steemJ.getAccounts(Arrays.asList(
            new AccountName[] { 
                new AccountName(vote.author.name), 
                new AccountName(vote.voter.name) 
            }
        ));

        Discussion discussion = steemJ.getContent(new AccountName(vote.author.name), new Permlink(vote.permlink));

        ExtendedAccount extendedAuthorAccount = extendedAccounts.get(0);
        ExtendedAccount extendedVoterAccount = extendedAccounts.get(1);

        BeanUtils.copyProperties(vote.author, extendedAuthorAccount);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(("HF version fetched: " + steemJ.getHardforkVersion()));
        log.info("Market ticker: " + steemJ.getTicker().toString());
        log.info("block: " + steemJ.getBlock(1));
    }

}
