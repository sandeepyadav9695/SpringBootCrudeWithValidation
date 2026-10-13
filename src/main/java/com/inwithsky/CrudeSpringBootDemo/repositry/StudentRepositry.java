package com.inwithsky.CrudeSpringBootDemo.repositry;

import com.inwithsky.CrudeSpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepositry extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findByDeletedIsFalse();

    boolean existsByEmailAndDeletedIsFalse(String email);

    boolean existsByEmail(String email);
}