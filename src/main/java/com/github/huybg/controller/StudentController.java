package com.github.huybg.controller;

import com.github.huybg.entity.Student;
import com.github.huybg.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("student-form", "command", new Student());
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addNewStudent(@ModelAttribute("abc") Student student, ModelMap modelMap) {
        if (studentService.insertStudent(student)) {
            return "redirect:student-list";
        }
        modelMap.addAttribute("insertFail", true);
        return "redirect:/";
    }

    @RequestMapping(value = "/student-list", method = RequestMethod.GET)
    public String getListStudent(ModelMap modelMap) {
        modelMap.put("students", studentService.getListStudent());
        return "student-list";
    }
}
