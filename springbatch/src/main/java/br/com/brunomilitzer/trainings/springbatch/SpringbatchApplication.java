package br.com.brunomilitzer.trainings.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringbatchApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringbatchApplication.class, args);
    }

}
