package br.com.brunomilitzer.trainings.springbatch.config;

import br.com.brunomilitzer.trainings.springbatch.batch.MyJobListener;
import br.com.brunomilitzer.trainings.springbatch.batch.Processor;
import br.com.brunomilitzer.trainings.springbatch.batch.Reader;
import br.com.brunomilitzer.trainings.springbatch.batch.Writter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory sbf;

    @Autowired
    private JobBuilderFactory jbf;

    @Bean
    public Job job() {
        return this.jbf.get("job1").incrementer(new RunIdIncrementer())
                .listener(this.listener()).start(this.step()).build();
    }

    @Bean
    public Step step() {
        return this.sbf.get("step1").<String, String>chunk(3).reader(this.reader())
                .writer(this.writter()).processor(this.processor()).build();
    }

    @Bean
    public Reader reader() {
        return new Reader();
    }

    @Bean
    public Writter writter() {
        return new Writter();
    }

    @Bean
    public Processor processor() {
        return new Processor();
    }

    @Bean
    public MyJobListener listener() {
        return new MyJobListener();
    }
}
