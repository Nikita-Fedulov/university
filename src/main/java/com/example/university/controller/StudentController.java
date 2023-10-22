package com.example.university.controller;


import com.example.university.model.Student;
import com.example.university.service.GroupService;
import com.example.university.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/students")
public class StudentController {

    StudentService studentService;
    GroupService groupService;


    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No students found");
        }
        model.addAttribute("students", students);
        return "student-list";
    }


    @GetMapping("/create")
    public String showStudentForm(Model model) {
        log.info("Showing student creation form");
        model.addAttribute("groups", groupService.getAllGroups());
        return "student-create";
    }


    @PostMapping("/create")
    public String createStudent(@RequestParam String fullname, @RequestParam LocalDate admissionDate, @RequestParam long groupId) {
        log.info("Creating student with fullname: {}, admissionDate: {}, groupId: {}", fullname, admissionDate, groupId);
        studentService.createStudent(fullname, admissionDate, groupId);
        return "redirect:/students";
    }


    @GetMapping("/{studentId}")
    public String viewStudent(@PathVariable long studentId, Model model) {
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "student-view";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }


    @GetMapping("/{studentId}/edit")
    public String editStudentForm(@PathVariable long studentId, Model model) {
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isPresent()) {
            model.addAttribute("student", student);
            model.addAttribute("groups", groupService.getAllGroups());
            return "student-edit";
        }
        return "redirect:/students";
    }


    @PostMapping("/{studentId}/edit")
    public String updateStudent(@PathVariable long studentId, @RequestParam String fullname, @RequestParam LocalDate admissionDate) {
        studentService.updateStudentInfo(studentId, fullname, admissionDate);
        return "redirect:/students/" + studentId;
    }


    @GetMapping("/{studentId}/delete")
    public String deleteStudent(@PathVariable long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }
}
