package br.com.brunomilitzer.springboot.springdatajpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 1817969197083910305L;

    @Id
    private Long id;

    private String name;

    private int testScore;

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

    public int getTestScore() {
        return this.testScore;
    }

    public void setTestScore(final int testScore) {
        this.testScore = testScore;
    }
}
