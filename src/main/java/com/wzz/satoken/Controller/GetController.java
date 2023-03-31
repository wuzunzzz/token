package com.wzz.satoken.Controller;

import com.wzz.satoken.pojo.Student;
import com.wzz.satoken.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class GetController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/get/{id}")
    public Student get(@PathVariable int id) {
        Student student = studentService.selectStudentById(id);
        return student;
    }

}
