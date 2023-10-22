package com.example.university.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @ManyToOne
    @JoinColumn(columnDefinition = "int", name = "group_id")
    private Group group;

    @Column(name = "fullname")
    private String fullname;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "admissionDate")
    private LocalDate admissionDate;

    public Student(String fullname, LocalDate admissionDate, Group group) {
        this.group = group;
        this.fullname = fullname;
        this.admissionDate = admissionDate;
    }
}
