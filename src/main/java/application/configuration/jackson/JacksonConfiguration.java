package application.configuration.jackson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.serializers.VoteDeserializer;

@Configuration
public class JacksonConfiguration { 

        @Bean
        public VoteDeserializer voteDeserializer() { 
            VoteDeserializer voteDeserializer = new VoteDeserializer();
            return voteDeserializer;
        }
    
}