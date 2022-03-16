package ru.kotikov.studentsgroups.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.studentsgroups.models.Student;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByFullName(String fullName);
}
