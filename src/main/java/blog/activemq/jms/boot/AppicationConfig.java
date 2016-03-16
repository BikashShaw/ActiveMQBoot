package blog.activemq.jms.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * Created by Bikash on 3/15/2016.
 */
@Configuration
public class AppicationConfig {
    @Bean
        // Required for durable subscription
    JmsListenerContainerFactory<?> durableJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSubscriptionDurable(true);
        factory.setClientId("cid001");
        return factory;
    }
}
