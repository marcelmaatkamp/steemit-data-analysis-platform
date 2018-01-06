package application;

import application.model.database.VoteRelation;
import application.model.json.Vote;
import application.repository.*;
import application.repository.streaming.VoteRelationRepository;
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
import org.springframework.beans.BeanUtils;
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
    VoteRepository voteRepository;

    @Autowired
    ExtendedAccountRepository extendedAccountRepository;

    @Autowired
    VoteStateRepository voteStateRepository;

    @Autowired
    DiscussionRepository discussionRepository;

    @Autowired
    PermlinkRepository permlinkRepository;

    @Autowired
    VoteRelationRepository voteRelationRepository;

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
        log.info("vote: author(" + vote.author.name.name + "), voter(" + vote.voter.name.name + "), weigth(" + vote.weight + "), link(" + vote.permlink.getLink() + ")");

        List<ExtendedAccount> extendedAccounts = steemJ.getAccounts(Arrays.asList(
                new AccountName(vote.author.name.name),
                new AccountName(vote.voter.name.name)));

        // author
        ExtendedAccount extendedAuthorAccount = extendedAccounts.get(0);
        application.model.steemj.ExtendedAccount extendedAuthorAccountNeo4j = extendedAccountRepository.findByName(extendedAuthorAccount.getName().getName());
        if (extendedAuthorAccountNeo4j == null) {
            extendedAuthorAccountNeo4j = new application.model.steemj.ExtendedAccount();
            BeanUtils.copyProperties(extendedAuthorAccount, extendedAuthorAccountNeo4j);
            extendedAuthorAccountNeo4j = extendedAccountRepository.save(extendedAuthorAccountNeo4j);
        }

        // voter
        ExtendedAccount extendedVoterAccount = extendedAccounts.get(1);
        application.model.steemj.ExtendedAccount extendedVoterAccountNeo4j = extendedAccountRepository.findByName(extendedVoterAccount.getName().getName());
        if (extendedVoterAccountNeo4j == null) {
            extendedVoterAccountNeo4j = new application.model.steemj.ExtendedAccount();
            BeanUtils.copyProperties(extendedVoterAccount, extendedVoterAccountNeo4j);
            extendedVoterAccountNeo4j = extendedAccountRepository.save(extendedVoterAccountNeo4j);
        }

        // permlink
        application.model.steemj.Permlink permlink = permlinkRepository.findByLink(vote.permlink.getLink());
        if (permlink == null) {
            permlink = permlinkRepository.save(vote.permlink);
        }

        // voteRelation
        VoteRelation voteRelation = new VoteRelation();
        voteRelation.author = extendedAuthorAccountNeo4j;
        voteRelation.voter = extendedVoterAccountNeo4j;
        voteRelation.permlink = permlink;
        voteRelation.weight = vote.weight * extendedVoterAccount.getVotingPower();
        voteRelation = voteRelationRepository.save(voteRelation);

        // Discussion discussion = steemJ.getContent(new AccountName(vote.author.name.name), new Permlink(vote.permlink.getLink()));
        // application.model.steemj.Discussion discussionNedo4j = new application.model.steemj.Discussion();
        // BeanUtils.copyProperties(discussion, discussionNedo4j);
        // discussionRepository.save(discussionNedo4j);


        // application.model.steemj.ExtendedAccount extendedAuthorAccountNeo4j = new application.model.steemj.ExtendedAccount();
        // BeanUtils.copyProperties(extendedAuthorAccount, extendedAuthorAccountNeo4j);
        // log.info("extendedAuthorAccount: " + ReflectionToStringBuilder.toString(extendedAuthorAccount));
        // log.info("extendedAuthorAccountNeo4j: " + ReflectionToStringBuilder.toString(extendedAuthorAccountNeo4j));
        // extendedAuthorAccountNeo4j = extendedAccountRepository.save(extendedAuthorAccountNeo4j);
        // vote.author = extendedAuthorAccountNeo4j;

        // log.info("extendedVoterAccount: " + ReflectionToStringBuilder.toString(extendedVoterAccount));
        // log.info("extendedVoterAccountNeo4j: " + ReflectionToStringBuilder.toString(extendedVoterAccountNeo4j));
        // extendedVoterAccountNeo4j = extendedAccountRepository.save(extendedVoterAccountNeo4j);

        // VoteState voteState = new VoteState();
        // voteState.setVoter(vote.voter.name);

        // voteState.setWeight(BigInteger.valueOf(vote.weight));
        // voteState = voteStateRepository.save(voteState);
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
