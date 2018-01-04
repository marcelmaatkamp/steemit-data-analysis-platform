import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.codehaus.jettison.mapped.MappedXMLOutputFactory;
import org.junit.Test;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;

public class XmlTest {

    @Test
    public void testXStream() {
        String json = "{\"entity\":[{\"id\":\"1\", \"name\":\"aaa\"}, {\"id\":\"2\", \"name\":\"bbb\"}]}";

        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        System.out.println("testXStream: "+(Object[])xstream.fromXML(json));

        assertFalse(false);

    }

    @Test
    public void testObjectMapper() {
        String json = "{\"entity\":[{\"id\":\"1\", \"name\":\"aaa\"}, {\"id\":\"2\", \"name\":\"bbb\"}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(json);

        System.out.println("testObjectMapper: "+jsonNode.asText());

        assertFalse(false);

    }

    @Test
    public void xmlTest() throws XMLStreamException {
        String xml = "<root><foo>foo string</foo><bar><x>1</x><y>5</y></bar></root>";

        StringWriter stringWriter = new StringWriter();
        XMLEventReader xmlEventReader = XMLInputFactory.newInstance()
                .createXMLEventReader(new StringReader(xml));
        XMLEventWriter writer = new MappedXMLOutputFactory(new HashMap<>())
                .createXMLEventWriter(stringWriter);
        writer.add(xmlEventReader);
        writer.close();

        System.out.println(stringWriter.toString());
        assertFalse(false);


    }


    @Test
    public void xmlJsonTest() throws XMLStreamException {
        String xml = "<root><foo>foo string</foo><bar><x>1</x><y>5</y></bar></root>";

        StringWriter stringWriter = new StringWriter();
        XMLEventReader xmlEventReader = XMLInputFactory.newInstance()
                .createXMLEventReader(new StringReader(xml));
        XMLEventWriter writer = new MappedXMLOutputFactory(new HashMap<>())
                .createXMLEventWriter(stringWriter);
        writer.add(xmlEventReader);
        writer.close();

        System.out.println(stringWriter.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(stringWriter.toString());

        System.out.println("testObjectMapper: "+jsonNode.asText());

        assertFalse(false);


    }

}