import lombok.extern.slf4j.Slf4j;
import oracle.soda.*;
import oracle.soda.rdbms.OracleRDBMSClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;


@Slf4j
public class OracleJsonTest {

    @Autowired
    Connection connection;

    @Test
    public void oracleJsonTest() throws OracleException {

        OracleRDBMSClient cl = new OracleRDBMSClient();
        OracleDatabase db = cl.getDatabase(connection);
        OracleCollection col = db.admin().createCollection("MyJSONCollection");
        OracleDocument doc = db.createDocumentFromString("{ \"name\" : \"Alexander\" }");

        String k = col.insertAndGet(doc).getKey();
        System.out.println("Inserted content:" +
                col.find().key(k).getOne().getContentAsString());

        OracleDocument f = db.createDocumentFromString("{\"name\" : { \"$startsWith\" : \"A\" }}");
        OracleCursor c = col.find().filter(f).getCursor();

        while (c.hasNext()) {
            OracleDocument resultDoc = c.next();
            log.info("Key:         " + resultDoc.getKey());
            log.info("Content:     " + resultDoc.getContentAsString());
        }
    }
}
