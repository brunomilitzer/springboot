package br.com.brunomilitzer.trainings.springboot.repository;

import br.com.brunomilitzer.trainings.springboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
