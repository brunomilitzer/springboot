package br.com.brunomilitzer.springboot.springdatajpa;

import br.com.brunomilitzer.springboot.springdatajpa.model.Student;
import br.com.brunomilitzer.springboot.springdatajpa.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringdatajpaApplicationTests {

    @Autowired
    private StudentRepository repository;

    @Test
    void testSaveStudent() {
        final Student student = new Student();
        student.setId(1l);
        student.setName("Vanessa");
        student.setTestScore(10);

        this.repository.save(student);

        final Student result = this.repository.findById(1l).get();

        assertNotNull(result);
    }
}
