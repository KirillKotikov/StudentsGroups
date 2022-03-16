package ru.kotikov.studentsgroups.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.studentsgroups.models.Group;

public interface GroupRepo extends JpaRepository<Group, Long> {
    Group findByGroupNumber(String groupNumber);
}
