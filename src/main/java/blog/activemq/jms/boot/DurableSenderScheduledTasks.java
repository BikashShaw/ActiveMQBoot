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
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bikash on 3/15/2016.
 */
@Component
public class DurableSenderScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ConfigurableApplicationContext context;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {

        jmsTemplate.send("simpleTopic", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Durable Message sent at " + dateFormat.format(new Date()));
            }
        });
    }

    @PostConstruct
    public void postConstruct() {
        ConnectionFactory  connectionFactory = (ConnectionFactory) context.getBean(ConnectionFactory.class);
        jmsTemplate.setConnectionFactory(connectionFactory);
    }
}
