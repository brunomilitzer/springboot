package br.com.brunomilitzer.trainings.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double testscore;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getTestscore() {
        return this.testscore;
    }

    public void setTestscore(final Double testscore) {
        this.testscore = testscore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", testscore=" + this.testscore +
                '}';
    }
}
