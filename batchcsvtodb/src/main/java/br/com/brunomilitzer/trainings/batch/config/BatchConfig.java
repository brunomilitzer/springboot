package br.com.brunomilitzer.trainings.batch.config;

import br.com.brunomilitzer.trainings.batch.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory sbf;

    @Autowired
    private JobBuilderFactory jbf;

    @Bean
    public Step step() {
        return this.sbf.get("s1").<Product, Product>chunk(3).reader(this.reader()).processor(this.processor()).writer(this.writer()).build();
    }

    @Bean
    public Job job() {
        return this.jbf.get("j1").incrementer(new RunIdIncrementer()).start(this.step()).build();
    }

    @Bean
    public ItemReader<Product> reader() {
        final FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("products.csv"));

        final DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "description", "price");
        final BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Product.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public ItemProcessor<Product, Product> processor() {
        return (p) -> {
            p.setPrice(p.getPrice() - p.getPrice() * 10 / 100);
            return p;
        };
    }

    @Bean
    public ItemWriter<Product> writer() {

        final JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(this.dataSource());
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO PRODUCT (name, description, price) VALUES (:name, :description, :price)");

        return writer;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3308/jdbcservlets?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("bmilitzer");
        dataSource.setPassword("s8mbt10a");

        return dataSource;
    }
}
