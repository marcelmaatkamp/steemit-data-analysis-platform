package json;

import static org.junit.Assert.assertEquals;

import java.beans.PropertyDescriptor;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.model.Account;

import org.junit.Test;

import json.model.Vote;

public class TestJSONConverter {

    @Test
    public void testConverter() throws JsonParseException, JsonMappingException, IOException {
        String jsonString = "{\"voter\": \"lauutorrez\", \"author\": \"blavin\", \"permlink\": \"seven-day-black-and-white-challenge-1st-entry\", \"weight\": 1000}";

        ObjectMapper mapper = new ObjectMapper();
        Vote vote = mapper.readValue(jsonString, json.model.Vote.class);
        System.out.println(vote);

        assertEquals(vote.voter.name, "lauutorrez");
    }

    @Test 
    public void testWriteableMethods() { 
        Class<?> actualEditable = Account.class;
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();

    }
}