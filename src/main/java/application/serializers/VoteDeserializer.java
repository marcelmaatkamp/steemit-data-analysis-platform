package application.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import org.springframework.beans.factory.annotation.Autowired;

import application.model.Person;
import application.model.Vote;
import application.repository.PersonRepository;

// {"voter": "lauutorrez", "author": "blavin", "permlink": "seven-day-black-and-white-challenge-1st-entry", "weight": 1000}

public class VoteDeserializer extends StdDeserializer<Vote> { 
 
    @Autowired
    PersonRepository personRepository;

    public VoteDeserializer() { 
        this(null); 
    } 
 
    public VoteDeserializer(Class<?> vc) { 
        super(vc); 
    }
 
    @Override
    public Vote deserialize(JsonParser jp, DeserializationContext ctxt) 
      throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        String permlink = node.get("permlink").asText();
        int weight = (Integer) ((IntNode) node.get("id")).numberValue();

        // TODO: Voter and Author: Neo4j get person by name
        String voterName = node.get("voter").asText();
        Person voter = personRepository.findByName(voterName);
        if(voter==null) { 
            voter = new Person(voterName);
            personRepository.save(voter);
        }
        String authorName = node.get("author").asText();
        Person author = personRepository.findByName(authorName);
        if(author==null) { 
            author = new Person(authorName);
            personRepository.save(author);
        }

        return new Vote(voter, author, permlink, weight);
    }
}