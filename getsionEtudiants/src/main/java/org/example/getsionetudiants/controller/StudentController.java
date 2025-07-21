package org.example.getsionetudiants.controller;

import org.example.getsionetudiants.model.Student;
import org.example.getsionetudiants.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("/list")
    public String getStudents(Model model){
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "student/studentList";
    }

    @GetMapping("/detail/{id}")
    public String getStudentDetails(@PathVariable("id") UUID id, Model model){
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student/studentDetails";
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/studentForm";
    }

    @PostMapping("/add")
    public String addStudent(Student student){
        studentService.addStudent(student);
        return "redirect:/student/list";
    }

    @GetMapping("/search")
    public String searchStudent(@RequestParam(value = "nom", required = false) String nom, Model model){
        if (nom != null && !nom.trim().isEmpty()) {
            List<Student> students = studentService.searchByName(nom);
            model.addAttribute("students", students);
            model.addAttribute("searchTerm", nom);
        }
        return "student/searchResults";
    }
}

