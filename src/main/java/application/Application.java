package application;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import application.model.Vote;
import application.repository.PersonRepository;
import application.repository.VoteRepository;
import eu.bittrade.libs.steemj.SteemJ;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableNeo4jRepositories
public class Application {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SteemJ steemj;

    @RabbitListener(bindings =
        @QueueBinding(
            exchange = @Exchange(value = "amq.topic", type = ExchangeTypes.TOPIC, durable = "true"),
            value = @Queue(value = "steemit.api.votes", durable = "true"),
            key = "steemit.vote"
        )
    )
    public void process(byte[] message) throws JsonParseException, JsonMappingException, IOException {
        Vote vote = voteRepository.save(objectMapper.readValue(new String(message), Vote.class));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
