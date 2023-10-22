package com.example.university.dao;

import com.example.university.model.Group;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Data
public class StudentDAO {
    long id;
    Group group;
    String fullname;
    LocalDate admissionDate;

}
