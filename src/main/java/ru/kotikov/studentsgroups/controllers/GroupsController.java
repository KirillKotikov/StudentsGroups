package ru.kotikov.studentsgroups.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kotikov.studentsgroups.models.Group;
import ru.kotikov.studentsgroups.models.Student;
import ru.kotikov.studentsgroups.repositories.GroupRepo;
import ru.kotikov.studentsgroups.repositories.StudentRepo;

import java.util.*;

@Controller
public class GroupsController {

    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/groups")
    public String groupsList(Model model) {
        model.addAttribute("tittle", "Группы университета ScaleApps");
        Iterable<Group> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        GroupsController groupsController = new GroupsController();
        model.addAttribute("groupsController", groupsController);
        return "groups";
    }

    @PostMapping("/groups")
    public String addGroup(@RequestParam String groupNumber, Model model) {
        Group group = new Group(groupNumber, new Date());
        groupRepo.save(group);
        String id = "" + groupRepo.findByGroupNumber(groupNumber).getId();
        return "redirect:/groups/" + id;
    }

    @GetMapping("/groups/{id}")
    public String studentsGroup(@PathVariable(value = "id") Long id, Model model) {
        if (!groupRepo.existsById(id)) return "redirect:/groups";
        Group group = groupRepo.findById(id).get();
        model.addAttribute("group", group);
        model.addAttribute("tittle", "Группа №" + group.getGroupNumber());
        List<Student> students = group.getStudents();
        students.sort((Comparator.comparingInt(o -> o.getFullName().hashCode())));
        model.addAttribute("students", students);
        return "studentsGroup";
    }
}
