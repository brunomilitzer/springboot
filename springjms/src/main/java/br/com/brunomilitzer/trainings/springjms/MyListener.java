package br.com.brunomilitzer.trainings.springjms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    @JmsListener(destination = "${springjms.myQueue}")
    public void receive(final String message) {
        System.out.println("Message received ===> " + message);
    }
}
