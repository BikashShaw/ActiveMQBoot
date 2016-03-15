package blog.activemq.jms.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bikash on 3/15/2016.
 */
@Component
public class SenderScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        jmsTemplate.send("simpleTopic", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("The time is now " + dateFormat.format(new Date()));
            }
        });
    }
}
