package br.com.brunomilitzer.trainings.thymeleaf.controllers;

import br.com.brunomilitzer.trainings.thymeleaf.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/sendData")
    public ModelAndView sendData() {
        final ModelAndView mav = new ModelAndView("data");
        mav.addObject("message", "Take up one idea and make your life");

        return mav;
    }

    @RequestMapping("/student")
    public ModelAndView getStudent() {

        final Student student = new Student();
        student.setName("Vanessa de Garcez");
        student.setScore(90);

        final ModelAndView mav = new ModelAndView("student");
        mav.addObject("student", student);

        return mav;
    }

    @RequestMapping("/students")
    public ModelAndView getStudents() {

        final Student student1 = new Student();
        student1.setName("Vanessa de Garcez");
        student1.setScore(90);

        final Student student2 = new Student();
        student2.setName("Tales de Garcez");
        student2.setScore(50);

        final List<Student> students = Arrays.asList(student1, student2);

        final ModelAndView mav = new ModelAndView("studentList");
        mav.addObject("students", students);

        return mav;
    }

    @RequestMapping("/studentForm")
    public ModelAndView displayStudentForm() {

        final Student student = new Student();
        student.setName("Bruno Militzer");
        student.setScore(80);

        final ModelAndView mav = new ModelAndView("studentForm");
        mav.addObject("student", student);

        return mav;
    }

    @RequestMapping("/saveStudent")
    public ModelAndView saveStudent(@ModelAttribute final Student student) {

        final ModelAndView mav = new ModelAndView("result");

        System.out.println(student.getName());
        System.out.println(student.getScore());
        
        return mav;
    }
}
