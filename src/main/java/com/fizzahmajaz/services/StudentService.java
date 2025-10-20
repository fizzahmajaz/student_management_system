package com.fizzahmajaz.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fizzahmajaz.model.StudentModel;
import com.fizzahmajaz.repository.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    //add student method
    public StudentModel addStudent(StudentModel studentModel){
        return studentRepository.save(studentModel);
    }

    //show all students method
    public List<StudentModel> getAllStudents(){
        return studentRepository.findAll();
    }

    // ============== Customised Search ==================

    // ============== Search by ID ==================

    // ============== Search By RollNumber ==================

    // ============== Search By Name ==================

    // ============== Customised Search ==================

    // ============== Customised Search ==================

    // ============== Customised Search ==================





    //find student by id
    public Optional<StudentModel> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    //find student by id
    public List<StudentModel> getStudentByName(String name){
        return studentRepository.findByName(name);
    }

    //find student by id
    public List<StudentModel> getStudentByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    //find student by id
    public List<StudentModel> getStudentByCourse(String course){
        return studentRepository.findByCourse(course);
    }

    //find student by id
    public List<StudentModel> getStudentByGrade(String grade){
        return studentRepository.findByGrade(grade);
    }

    //find student by RollNumber
    public Optional<StudentModel> getStudentByRollNumber(String RollNumber){
        return studentRepository.findByRollNumber(RollNumber);
    }

    //====================================================

    //Update Student
    public StudentModel updateStudent(Long id, StudentModel studentModel){
        return studentRepository.findById(id).map(student -> {
            student.setName(studentModel.getName());
            student.setEmail(studentModel.getCourse());
            student.setCourse(studentModel.getCourse());
            student.setGrade(studentModel.getGrade());
            return studentRepository.save(student);
        }).orElse(null);
                                
    }


    //delete student method
    public boolean deleteStudent(Long id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }



    
}
