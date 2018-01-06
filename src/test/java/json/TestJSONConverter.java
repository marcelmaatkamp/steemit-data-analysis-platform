package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.model.Vote;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestJSONConverter {

    @Test
    public void testConverter() throws IOException {
        String jsonString = "{\"voter\": \"lauutorrez\", \"author\": \"blavin\", \"permlink\": \"seven-day-black-and-white-challenge-1st-entry\", \"weight\": 1000}";

        ObjectMapper mapper = new ObjectMapper();
        Vote vote = mapper.readValue(jsonString, json.model.Vote.class);
        System.out.println(vote);

        assertEquals(vote.voter.name, "lauutorrez");
    }

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