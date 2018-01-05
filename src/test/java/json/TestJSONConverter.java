package json;

import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;

import json.model.*;

import static org.junit.Assert.*;

public class TestJSONConverter {

    @Test
    public void testConverter() throws JsonParseException, JsonMappingException, IOException {
        String jsonString = "{\"voter\": \"lauutorrez\", \"author\": \"blavin\", \"permlink\": \"seven-day-black-and-white-challenge-1st-entry\", \"weight\": 1000}";

        ObjectMapper mapper = new ObjectMapper();        
        Vote vote = mapper.readValue(jsonString, json.model.Vote.class);
        System.out.println(vote);

        assertEquals(vote.voter.name, "lauutorrez");
    }
}