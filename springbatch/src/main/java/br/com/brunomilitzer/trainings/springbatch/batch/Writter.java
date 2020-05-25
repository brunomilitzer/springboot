package br.com.brunomilitzer.trainings.springbatch.batch;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class Writter implements ItemWriter<String> {

    @Override
    public void write(final List<? extends String> items) throws Exception {

        System.out.println("Inside Write");
        System.out.println("Writing Data: " + items);
    }
}
