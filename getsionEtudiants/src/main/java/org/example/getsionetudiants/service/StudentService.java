package org.example.getsionetudiants.service;


import org.example.getsionetudiants.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<UUID, Student> students;

    public StudentService() {
        students = new HashMap<>();
        Student student1 = new Student(UUID.randomUUID(), "Dupont", "Etdupont", 20, "dupont@example.com");
        Student student2 = new Student(UUID.randomUUID(), "Martin", "Mystere", 22, "martin@example.com");
        Student student3 = new Student(UUID.randomUUID(), "Kimousse", "Pierre", 21, "pierre@example.com");
        students.put(student1.getId(), student1);
        students.put(student2.getId(), student2);
        students.put(student3.getId(), student3);
    }

    public List<Student> getStudents() {
        return students.values().stream().toList();
    }

    public Student getStudent(UUID id) {
        return students.get(id);
    }

    public void addStudent(Student student) {
        UUID id = UUID.randomUUID();
        student.setId(id);
        students.put(id, student);
    }

    public void updateStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void removeStudent(UUID id) {
        students.remove(id);
    }

    public List<Student> searchByName(String nom) {
        return students.values().stream()
                .filter(student -> student.getNom().toLowerCase().contains(nom.toLowerCase()))
                .collect(Collectors.toList());
    }
}