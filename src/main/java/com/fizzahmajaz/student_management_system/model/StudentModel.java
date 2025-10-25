package com.fizzahmajaz.student_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rollNumber;
    private String email;
    private String course;
    private String grade;

    public StudentModel (String name, String rollNumber, String email, String course, String grade){
    this.name = name;
    this.rollNumber = rollNumber;
    this.email = email;
    this.course = course;
    this.grade = grade;
    }
    
}
