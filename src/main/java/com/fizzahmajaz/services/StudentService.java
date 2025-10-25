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

    // ======================== Customised Search =============================

    // ============== CRUD by ID ==================

    //find student by id
    public Optional<StudentModel> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    //Update student by id
    public StudentModel updateStudentById(Long id, StudentModel updatedData){
        Optional<StudentModel> existingStudent = studentRepository.findById(id);
        if(existingStudent.isPresent()){
            StudentModel newData = existingStudent.get();
            newData.setName(updatedData.getName());
            newData.setRollNumber(updatedData.getRollNumber());
            newData.setEmail(updatedData.getEmail());
            newData.setCourse(updatedData.getCourse());
            newData.setGrade(updatedData.getGrade());

            return studentRepository.save(newData);
        }
        else{
            return null;
        }

    }
    //Delete student by id
    public Boolean deleteStudentById(Long id){
        Optional<StudentModel> existingStudent = studentRepository.findById(id);
        if(existingStudent.isPresent()){
            studentRepository.delete(existingStudent.get());
            return true;
        }else{
            return false;
        }

    }

    // ============== CRUD By RollNumber ==================

    //find student by RollNumber
    public Optional<StudentModel> getStudentByRollNumber(String RollNumber){
        return studentRepository.findByRollNumber(RollNumber);
    }
    
    //Update student by Roll Number
    public StudentModel updateStudentByRollNumber(String rollNumber, StudentModel updatedData){
        Optional<StudentModel> existingStudent = studentRepository.findByRollNumber(rollNumber);
        if(existingStudent.isPresent()){
            StudentModel newData = existingStudent.get();
            newData.setName(updatedData.getName());
            newData.setRollNumber(updatedData.getRollNumber());
            newData.setEmail(updatedData.getEmail());
            newData.setCourse(updatedData.getCourse());
            newData.setGrade(updatedData.getGrade());

            return studentRepository.save(newData);
        }
        else{
            return null;
        }

    }
    //Delete student by Roll Number
    public Boolean deleteStudentByRollNumber(String rollNumber){
        Optional<StudentModel> existingStudent = studentRepository.findByRollNumber(rollNumber);
        if(existingStudent.isPresent()){
            studentRepository.delete(existingStudent.get());
            return true;
        }else{
            return false;
        }

    }

    
    // ============== CRUD By Email ==================

    //find student by Email
    public Optional<StudentModel> getStudentByEmail(String email){
        return studentRepository.findByEmail(email);
    }
    
    //Update student by Email
    public StudentModel updateStudentByEmail(String email, StudentModel updatedData){
        Optional<StudentModel> existingStudent = studentRepository.findByEmail(email);
        if(existingStudent.isPresent()){
            StudentModel newData = existingStudent.get();
            newData.setName(updatedData.getName());
            newData.setRollNumber(updatedData.getRollNumber());
            newData.setEmail(updatedData.getEmail());
            newData.setCourse(updatedData.getCourse());
            newData.setGrade(updatedData.getGrade());

            return studentRepository.save(newData);
        }
        else{
            return null;
        }

    }
    //Delete student by Email
    public Boolean deleteStudentByEmail(String email){
        Optional<StudentModel> existingStudent = studentRepository.findByEmail(email);
        if(existingStudent.isPresent()){
            studentRepository.delete(existingStudent.get());
            return true;
        }else{
            return false;
        }

    }

    // ============== Search By Name ==================

     //find student by Name
    public List<StudentModel> getStudentByName(String name){
        return studentRepository.findByName(name);
    }
    

    // ============== Search By Course ==================

    //find student by Course
    public List<StudentModel> getStudentByCourse(String course){
        return studentRepository.findByCourse(course);
    }
    


    // ============== Search By Grade ==================

    //find student by Grade
    public List<StudentModel> getStudentByGrade(String grade){
        return studentRepository.findByGrade(grade);
    }




    
}
