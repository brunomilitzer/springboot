package br.com.brunomilitzer.trainings.springbatch.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

    private final String[] courses = {"Java Web Services", "Spring Framework in Easy Steps", "JDBC Servlets and JSP"};

    private int count;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Inside Read Method");

        if (this.count < this.courses.length) {
            return this.courses[this.count++];
        } else {
            this.count = 0;
        }
        return null;
    }
}