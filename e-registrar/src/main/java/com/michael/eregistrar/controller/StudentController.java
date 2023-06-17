package com.michael.eregistrar.controller;

import com.michael.eregistrar.model.Student;
import com.michael.eregistrar.model.service.StudentService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

//    @GetMapping(value = "/index")
//    public String displayStudents(Model model) {
//        var students = studentService.getAll();
//        model.addAttribute("students", students);
//        return "students/index";
//    }

    @GetMapping(value = "/index")
    public String displayStudents(@RequestParam(required = false, defaultValue = "") String searchParam, Model model) {
        var students = studentService.searchStudents(searchParam);
        model.addAttribute("students", students);
        model.addAttribute("searchParam", searchParam);
        return "students/index";
    }

    @GetMapping(value = "/register")
    public String registerStudents(Model model) {
        var student= new Student();
        model.addAttribute("student", student);
        return "students/register";
    }

    @PostMapping(value = "/register")
    public String addNewStudent(@Valid @ModelAttribute Student newStudent,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "students/register";
        }
        var savedStudents= studentService.create(newStudent);
        return "redirect:/students/index";
    }

    @GetMapping(value = "/{studentId}/edit")
    public String editStudents(@ModelAttribute("studentId") Integer studentId, Model model) {
        var studentOpt = studentService.get(studentId);
        if (studentOpt.isPresent()) {
            var student = studentOpt.get();
            model.addAttribute("student", student);
            return "students/edit";
        }else {
            return "redirect:/students/index";
        }
    }

    @PostMapping(value = "/update")
    public String updateStudents(@Valid @ModelAttribute Student newStudent,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "students/edit";
        }
        var savedStudents= studentService.update(newStudent);
        return "redirect:/students/index";
    }

    @GetMapping(value = "/{studentId}/delete")
    public String deleteStudents(@PathVariable("studentId") Integer studentId) {
            studentService.delete(studentId);
            return "redirect:/students/index";
    }
//or we have another option
//    @GetMapping(value = "/{studentId}/delete")
//    public String deleteStudents(@ModelAttribute("studentId") Integer studentId) {
//        var studentOpt = studentService.get(studentId);
//        if (studentOpt.isPresent()) {
//            studentService.delete(studentId);
//        }
//        return "redirect:/students/index";
//    }


}
