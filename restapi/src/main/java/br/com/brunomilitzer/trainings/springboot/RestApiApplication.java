package br.com.brunomilitzer.trainings.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableCaching
public class RestApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

}
