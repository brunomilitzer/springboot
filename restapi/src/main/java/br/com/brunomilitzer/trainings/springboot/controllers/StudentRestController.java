package br.com.brunomilitzer.trainings.springboot.controllers;

import br.com.brunomilitzer.trainings.springboot.model.Student;
import br.com.brunomilitzer.trainings.springboot.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "students", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }

    @RequestMapping(value = "students/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable("id") final Long id) {
        LOGGER.info("Finding student by ID: " + id);
        return this.studentRepository.findById(id).get();
    }

    @RequestMapping(value = "students", method = RequestMethod.POST)
    public Student createStudent(@RequestBody final Student student) {
        return this.studentRepository.save(student);
    }

    @RequestMapping(value = "students", method = RequestMethod.PUT)
    public Student updateStudent(@RequestBody final Student student) {
        return this.studentRepository.save(student);
    }

    @RequestMapping(value = "students/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("id") final Long id) {
        this.studentRepository.deleteById(id);
    }
}
