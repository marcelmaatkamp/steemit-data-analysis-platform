package application;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import application.model.Vote;
import application.repository.PersonRepository;
import application.repository.VoteRepository;
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

    @RabbitListener(queues = "steemit.votes")
    @RabbitListener(bindings =
        @QueueBinding(
            value = @Queue(value = "steemit.votes", durable = "true"),
            exchange = @Exchange(value = "amq.topic", type = "topic", durable = "true")
        )
    )

    public void process(byte[] message) throws JsonParseException, JsonMappingException, IOException {
        String json = new String(message);

        ObjectMapper mapper = new ObjectMapper();
        Vote vote = mapper.readValue(json, Vote.class);
        voteRepository.save(vote);
        log.info(json, vote);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
