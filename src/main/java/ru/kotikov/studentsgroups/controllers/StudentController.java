package ru.kotikov.studentsgroups.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kotikov.studentsgroups.models.Group;
import ru.kotikov.studentsgroups.models.Student;
import ru.kotikov.studentsgroups.repositories.GroupRepo;
import ru.kotikov.studentsgroups.repositories.StudentRepo;

import java.util.Date;

@Controller
public class StudentController {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    GroupRepo groupRepo;

    @PostMapping("/groups/{id}")
    public String addStudent(@PathVariable(name = "id") Long id, @RequestParam String fullName, @RequestParam String groupNumber, Model model) {
        Group group = groupRepo.findAll().stream().filter(x -> x.getGroupNumber().equals(groupNumber)).findFirst().get();
        Student student = new Student(fullName, new Date(), group);
        studentRepo.save(student);
        return "redirect:/groups/" + group.getId();
    }

    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable(name = "id") Long id, Model model) {
        Student student = studentRepo.findById(id).get();
        studentRepo.delete(student);
        return "redirect:/groups/" + student.getGroup().getId();
    }
}
