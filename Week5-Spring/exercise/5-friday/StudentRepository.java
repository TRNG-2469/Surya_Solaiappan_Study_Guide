package com.rev.rest.springg.repositories;

import com.rev.rest.springg.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// integer is primary key id
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
