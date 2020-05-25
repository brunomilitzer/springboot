package br.com.brunomilitzer.trainings.springjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringjmsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringjmsApplication.class, args);
    }

}
