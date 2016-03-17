package blog.activemq.jms.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bikash on 3/15/2016.
 */
@Component
public class DurableSenderScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2500)
    public void reportCurrentTime() {

        jmsTemplate.send("simpleTopic", session -> {
            TextMessage textMessage = session.createTextMessage("Durable Message sent at " + dateFormat.format(new Date()));
            textMessage.setJMSCorrelationID("cid001");
            return textMessage;
        });
    }
}
