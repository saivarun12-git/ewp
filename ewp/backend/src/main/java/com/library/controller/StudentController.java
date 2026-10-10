package com.library.controller;

import com.library.entity.Student;
import com.library.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() { return studentService.getAllStudents(); }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) { return studentService.getStudentById(id); }

    @PostMapping
    public Student add(@RequestBody Student student) { return studentService.addStudent(student); }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) { return studentService.updateStudent(id, student); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { studentService.deleteStudent(id); }
}
