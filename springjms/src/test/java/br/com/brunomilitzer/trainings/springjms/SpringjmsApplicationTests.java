package br.com.brunomilitzer.trainings.springjms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringjmsApplicationTests {

    @Autowired
    private MessageSender sender;

    @Test
    void testSendAndRecieve() {
        this.sender.send("Hello Spring JMS!!!");
    }

}