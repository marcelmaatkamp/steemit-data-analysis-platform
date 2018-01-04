package application;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {

    @RabbitListener(bindings =
        @QueueBinding(
            value = @Queue(value = "json-oracle", durable = "true"),
            exchange = @Exchange(value = "json", type = "fanout", durable = "true")
        )
    )
    public void process(byte[] message) {
        String json = new String(message);
        log.info(json);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
