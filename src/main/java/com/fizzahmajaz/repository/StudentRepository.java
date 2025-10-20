package com.fizzahmajaz.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fizzahmajaz.model.StudentModel;

public interface StudentRepository extends JpaRepository<StudentModel, Long>{

    //Roll numbers and emails are unique thats why we are using Optional
    Optional<StudentModel> findByRollNumber(String rollNumber);
    Optional<StudentModel> findByEmail(String email);

    //Name, Course, Grade can't be unique thats why we are going to use List.
    List<StudentModel> findByName(String name);
    List<StudentModel> findByCourse(String course);
    List<StudentModel> findByGrade(String grade);


}
