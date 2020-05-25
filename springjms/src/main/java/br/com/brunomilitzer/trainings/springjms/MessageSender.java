package br.com.brunomilitzer.trainings.springjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${springjms.myQueue}")
    private String queue;

    public void send(final String message) {
        System.out.println("Message Sent ===> " + message);
        
        final MessageCreator mc = s -> s.createTextMessage("Hello Spring JMS!!!");

        this.jmsTemplate.send(this.queue, mc);
    }
}
