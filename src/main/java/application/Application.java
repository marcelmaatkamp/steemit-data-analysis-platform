package application;

import lombok.extern.slf4j.Slf4j;
import oracle.soda.OracleCollection;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    OracleDatabase oracleDatabase;

    @Autowired
    OracleCollection oracleCollection;

    @RabbitListener(bindings =
        @QueueBinding(
            value = @Queue(value = "json-oracle", durable = "true"),
            exchange = @Exchange(value = "json", type = "fanout", durable = "true")
        )
    )
    public void process(byte[] message) throws OracleException {
        String json = new String(message);
        log.info(json);

        OracleDocument doc = oracleDatabase.createDocumentFromString(json);
        String key = oracleCollection.insertAndGet(doc).getKey();

        log.info(String.format("key(%s): %s:",key,json));
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
