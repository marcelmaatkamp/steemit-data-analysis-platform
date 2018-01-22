package application.json;

import application.json.model.Person;
import application.json.model.Vote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestJSONConverter {

    String AUTHOR_NAME = "author";
    String VOTER_NAME = "voter";
    int VOTER_WEIGHT = 100;
    String PERMLINK = "http://permlink";

    @Test
    public void testWriteableMethods() {
        A a = new A();
        B b = new B();
        BeanUtils.copyProperties(a, b);
        assertEquals("b", b.s);
    }

    @Test
    public void testWriteableMethodsViaGetSet() {
        AGetSet a = new AGetSet();
        BGetSet b = new BGetSet();
        BeanUtils.copyProperties(a, b);
        assertEquals("a", b.s);
    }

    @Test
    public void testWriteableMethodsViaAGetSet() {
        AGetSet a = new AGetSet();
        B b = new B();
        BeanUtils.copyProperties(a, b);
        assertEquals("b", b.s);
    }

    @Test
    public void testWriteableMethodsViaBGetSet() {
        A a = new A();
        BGetSet b = new BGetSet();
        BeanUtils.copyProperties(a, b);
        assertEquals("b", b.s);
    }

    @Test
    public void testConverter() throws IOException {
        String jsonString = "{\"voter\": \"lauutorrez\", \"author\": \"blavin\", \"permlink\": \"seven-day-black-and-white-challenge-1st-entry\", \"weight\": 1000}";
        ObjectMapper mapper = new ObjectMapper();
        Vote vote = mapper.readValue(jsonString, Vote.class);
        assertEquals(vote.voter.name, "lauutorrez");
    }

    @Test
    public void xstreamToJsonAndBackToObject() {

        Vote vote = new Vote();
        vote.author = new Person(AUTHOR_NAME);
        vote.voter = new Person(VOTER_NAME);
        vote.weight = VOTER_WEIGHT;
        vote.permlink = PERMLINK;

        // to json
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.processAnnotations(Vote.class);
        String json = xstream.toXML(vote);

        // and back to object
        XStream xstreamFromJson = new XStream(new JettisonMappedXmlDriver());
        xstreamFromJson.processAnnotations(Vote.class);
        Vote sameVote = (Vote) xstreamFromJson.fromXML(json);

        // test
        assertEquals(AUTHOR_NAME, sameVote.author.name);
        assertEquals(VOTER_NAME, sameVote.voter.name);
        assertEquals(VOTER_WEIGHT, sameVote.weight);
        assertEquals(PERMLINK, sameVote.permlink);
    }

    public class A {
        public String s = "a";
    }

    public class AGetSet {
        public String s = "a";

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }

    public class B {
        public String s = "b";
    }

    public class BGetSet {
        public String s = "b";

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }
}