package com.example.university.dao;

import com.example.university.model.Student;
import lombok.Data;

import java.util.List;

@Data
public class GroupDAO {
    long id;
    String name;
    List<Student> students;
}
