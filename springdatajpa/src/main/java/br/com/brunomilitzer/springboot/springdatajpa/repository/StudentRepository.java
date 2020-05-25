package br.com.brunomilitzer.springboot.springdatajpa.repository;

import br.com.brunomilitzer.springboot.springdatajpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
