package br.com.brunomilitzer.trainings.springboot;

import br.com.brunomilitzer.trainings.springboot.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentRestControllerMVCTest extends RestControllerMVCTests {

    private static final long STUDENT_ID = 1L;
    private static final String STUDENT_NAME = "Gustavo Fring";
    private static final double STUDENT_TEST_SCORE = 80.50;
    private static final long serialVersionUID = -2927359079828269797L;

    @Test
    public void testFindAll() throws Exception {

        final List<Student> students = Collections.singletonList(this.createStudent());
        when(this.getStudentRepository().findAll()).thenReturn(students);

        this.getMockMvc().perform(get(STUDENT_URL_PATH).contextPath(CONTEXT_PATH)).andExpect(status().isOk())
                .andExpect(content().json(this.objectWritter().writeValueAsString(students)));
    }

    @Test
    public void testFindById() throws Exception {
        final Student student = this.createStudent();

        when(this.getStudentRepository().findById(any())).thenReturn(Optional.of(student));

        this.getMockMvc().perform(get(STUDENT_URL_PATH + "/" + STUDENT_ID).contextPath(CONTEXT_PATH)).andExpect(status().isOk())
                .andExpect(content().json(this.objectWritter().writeValueAsString(student)));
    }

    @Test
    public void testCreateStudent() throws Exception {
        final Student student = this.createStudent();
        when(this.getStudentRepository().save(any())).thenReturn(student);

        this.getMockMvc().perform(post(STUDENT_URL_PATH).contextPath(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content(this.objectWritter()
                .writeValueAsString(student))).andExpect(status().isOk()).andExpect(content().json(this.objectWritter().writeValueAsString(student)));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        final Student student = this.createStudent();
        student.setTestscore(50.00);
        when(this.getStudentRepository().save(any())).thenReturn(student);

        this.getMockMvc().perform(put(STUDENT_URL_PATH).contextPath(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content(this.objectWritter()
                .writeValueAsString(student))).andExpect(status().isOk()).andExpect(content().json(this.objectWritter().writeValueAsString(student)));
    }

    @Test
    public void deleteStudent() throws Exception {
        doNothing().when(this.getStudentRepository()).deleteById(STUDENT_ID);

        this.getMockMvc().perform(delete(STUDENT_URL_PATH + "/" + STUDENT_ID).contextPath(CONTEXT_PATH)).andExpect(status().isOk());
    }

    private ObjectWriter objectWritter() {
        return new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    private Student createStudent() {
        final Student student = new Student();
        student.setId(STUDENT_ID);
        student.setName(STUDENT_NAME);
        student.setTestscore(STUDENT_TEST_SCORE);
        return student;
    }
}
