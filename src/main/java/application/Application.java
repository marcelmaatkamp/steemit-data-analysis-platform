package application;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import application.model.Vote;
import application.repository.PersonRepository;
import application.repository.VoteRepository;
import application.serializers.VoteDeserializer;

@SpringBootApplication
@Slf4j
@EnableNeo4jRepositories
public class Application {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    PersonRepository personRepository;

    @RabbitListener(queues = "steemit.votes")
    public void process(byte[] message) throws JsonParseException, JsonMappingException, IOException {
        String json = new String(message);

        ObjectMapper mapper = new ObjectMapper();
        Vote vote = mapper.readValue(message, Vote.class);

        SimpleModule module = new SimpleModule();
        VoteDeserializer voteDeserializer = new VoteDeserializer();
        voteDeserializer.personRepository = this.personRepository;
        module.addDeserializer(Vote.class, voteDeserializer);
        mapper.registerModule(module);

        voteRepository.save(vote);
        log.info(json, vote);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
