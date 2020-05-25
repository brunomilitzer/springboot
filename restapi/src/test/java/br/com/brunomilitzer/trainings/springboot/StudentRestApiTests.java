package br.com.brunomilitzer.trainings.springboot;

import br.com.brunomilitzer.trainings.springboot.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class StudentRestApiTests {

    @Value("${base.url}")
    private String baseUrl;

    @Test
    void testGetStudent() {

        final RestTemplate restTemplate = new RestTemplate();
        final Student student = restTemplate.getForObject(this.baseUrl + "students/1", Student.class);

        assertNotNull(student);
        assertEquals("Vanessa de Garcez", student.getName());
    }

    @Test
    void testCreateStudent() {

        final Student student = new Student();
        student.setName("Vanessa de Garcez");
        student.setTestscore(90.50);

        final RestTemplate restTemplate = new RestTemplate();
        final Student newStudent = restTemplate.postForObject(this.baseUrl + "students", student, Student.class);

        assertNotNull(newStudent);
        assertNotNull(newStudent.getId());
        assertEquals("Vanessa de Garcez", newStudent.getName());
    }

    @Test
    void testUpdateStudent() {

        final RestTemplate restTemplate = new RestTemplate();
        final Student student = restTemplate.getForObject(this.baseUrl + "students/1", Student.class);

        assertNotNull(student);
        assertNotNull(student.getId());

        student.setTestscore(80.3);

        restTemplate.put(this.baseUrl + "students", student);

        assertEquals("Galaxy S20", student.getName());
        assertEquals(80.3, student.getTestscore());
    }
}
