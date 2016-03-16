package blog.activemq.jms.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Bikash on 3/14/2016.
 */
@Component
public class ReceiverTwoNonDurable {


    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     */
    @JmsListener(destination = "simpleTopic")
    public void receiveMessage(String message) {
        System.out.println(this.getClass().getName() + " Received <" + message + ">");
    }
}
