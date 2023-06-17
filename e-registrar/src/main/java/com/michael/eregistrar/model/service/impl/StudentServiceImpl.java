package com.michael.eregistrar.model.service.impl;

import com.michael.eregistrar.model.Student;
import com.michael.eregistrar.model.service.StudentService;
import com.michael.eregistrar.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> get(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public List<Student> searchStudents(String param) {
        return studentRepository.searchStudents(param);
    }

//    @Override
//    public List<Student> searchStudents(String param) {
//        return studentRepository.findAllByStudentNumberContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrMiddleNameContainingOrLastNameContainingOrIsInternationalContainingIgnoreCase(param, param, param, param, param);
//    }


}
