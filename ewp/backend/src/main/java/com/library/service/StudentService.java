package com.library.service;

import com.library.entity.Student;
import com.library.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing == null) return null;
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setDepartment(student.getDepartment());
        existing.setPhone(student.getPhone());
        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
