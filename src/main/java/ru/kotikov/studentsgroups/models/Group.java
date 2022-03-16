package ru.kotikov.studentsgroups.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "studentsGroup")
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String groupNumber;
    @Column(nullable = false)
    private Date dateAdded;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<Student> students;

    public Group(String groupNumber, Date dateAdded) {
        this.groupNumber = groupNumber;
        this.dateAdded = dateAdded;
    }
}
