package com.wzz.satoken.service.Impl;

import com.wzz.satoken.Mapper.StudentMapper;
import com.wzz.satoken.pojo.Student;
import com.wzz.satoken.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student selectStudentById(int id) {
        Student student = studentMapper.selectById(id);

        return student;
    }
}
