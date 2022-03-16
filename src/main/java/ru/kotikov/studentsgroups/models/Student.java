package ru.kotikov.studentsgroups.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "student")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String dateAdded;
    @ManyToOne
    @JoinColumn(name = "groupId", nullable = false)
    private Group group;

    public void setDateAdded(Date dateAdded) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        this.dateAdded = df.format(dateAdded);
    }

    public Student(String fullName, Date dateAdded, Group group) {
        this.fullName = fullName;
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        this.dateAdded = df.format(dateAdded);
        this.group = group;
    }
}
