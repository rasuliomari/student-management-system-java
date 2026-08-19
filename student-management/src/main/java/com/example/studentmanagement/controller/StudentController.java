package com.example.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Display all students
    @GetMapping
    public String students(Model model) {

        model.addAttribute(
                "students",
                studentRepository.findAll()
        );

        return "students";
    }

    // Show empty form for new student
    @GetMapping("/new")
    public String showStudentForm(Model model) {

        model.addAttribute("student", new Student());

        return "student-form";
    }

    // Show form for editing existing student
    @GetMapping("/edit/{id}")
    public String editStudent(
            @PathVariable Long id,
            Model model) {

        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid student ID: " + id
                        ));

        model.addAttribute("student", student);

        return "student-form";
    }

    // Save new or edited student
    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute Student student) {

        studentRepository.save(student);

        return "redirect:/students";
    }

    // Delete student
    @GetMapping("/delete/{id}")
    public String deleteStudent(
            @PathVariable Long id) {

        studentRepository.deleteById(id);

        return "redirect:/students";
    }
}