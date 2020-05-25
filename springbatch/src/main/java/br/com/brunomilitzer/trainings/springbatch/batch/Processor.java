package br.com.brunomilitzer.trainings.springbatch.batch;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {

    @Override
    public String process(final String item) throws Exception {

        System.out.println("Inside Process");

        return "PROCESSED " + item.toUpperCase();
    }
}
