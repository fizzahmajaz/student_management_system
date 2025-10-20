package com.fizzahmajaz.controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.fizzahmajaz.model.StudentModel;
import com.fizzahmajaz.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
@Data
@AllArgsConstructor
public class StudentConsoleController {

    @Autowired
    private final StudentService studentService;
     Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args){
       
        int choice;
        do{
            System.out.println("\n==== STUDENT MANAGEMENT SYSTEM ====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Students");
            System.out.println("6. Exist");
            System.out.println("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                addStudent();

                case 2:
                ViewAllStudent();

                case 3:
                searchStudentByID();

                case 4:
                searchStudentByRollNumber();

                case 5:
                studentService.updateStudent(null, null);

                case 6:
                studentService.deleteStudent(null);

                case 7:
                System.out.println("Exiting... Goodbye!");
                    
                break;
            
                default:
                System.out.println("Invalid choice!");
            }
        }while(choice != 7);   
    }

    //method to add students
    private void addStudent(){
        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter course: ");
        String course = scanner.nextLine();

        System.out.println("Enter grade: ");
        String grade = scanner.nextLine();

        StudentModel studentModel = new StudentModel(name, email, course, grade);
        studentService.addStudent(studentModel);
        System.out.println("Student added successfully!");

    }

    //method to vew all students
    private void ViewAllStudent(){
        List<StudentModel> studentModels = studentService.getAllStudents();
        System.out.println("\nID | Name | Email | Course | Grade");
        studentModels.forEach(System.out::println);
    }

    //Get students by id
    private void searchStudentByID(){
        System.out.println("Enter student ID:");
        Long id = scanner.nextLong();
        Optional<StudentModel> studentModel = studentService.getStudentById(id);
        studentModel.ifPresentOrElse(System.out::println, ()-> System.out.println("Student not found"));
    }

    //Get students by rollNumber
    private void searchStudentByRollNumber(){
        System.out.println("Enter student ID:");
        Long id = scanner.nextLong();
        Optional<StudentModel> studentModel = studentService.getStudentById(id);
        studentModel.ifPresentOrElse(System.out::println, ()-> System.out.println("Student not found"));
    }



}
