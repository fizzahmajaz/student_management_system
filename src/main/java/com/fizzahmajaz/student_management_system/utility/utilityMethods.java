package com.fizzahmajaz.student_management_system.utility;

import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.fizzahmajaz.student_management_system.model.StudentModel;

@Component
public class utilityMethods {

    private final Scanner scanner = new Scanner(System.in);

    //For clearing screen before every loop
    public void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }


    //For pausing after every task
    public void pause(){
        System.out.println("\n Press Enter to continue...");
        scanner.nextLine();
    }

    //For printing the header
    public void printHeader(String title, String developer){
        clearScreen();
        System.out.println("\n========================================================");
        System.out.println("        " + title.toUpperCase());
        System.out.println("==========================================================");
        System.out.println("        " + developer.toUpperCase());
        System.out.println();

    }

    //For taking the student data
    public StudentModel studentDataInput(Long id){

        System.out.println("Enter the student's name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the student's Roll Number: ");
        String rollNumber = scanner.nextLine();

        System.out.println("Enter the student's email: ");
        String email = scanner.nextLine();

        System.out.println("Enter the student's course: ");
        String course = scanner.nextLine();

        System.out.println("Enter the student's grade: ");
        String grade = scanner.nextLine();

        StudentModel student = new StudentModel();
        student.setId(id);
        student.setName(name);
        student.setRollNumber(rollNumber);
        student.setEmail(email);
        student.setCourse(course);
        student.setGrade(grade);

        return student;
    }

    // public StudentModel deleteStudent(String varName, ){
    //     String varName = scanner.nextLine();
    //     scanner.nextLine();
    //     boolean isDeleted = studentService.deleteStudentById(id);
    //     if (isDeleted) {
    //         System.out.println("Student deleted successfully");
    //         utilityMethods.pause();
    //     } else {
    //         System.out.println("Student not found");
    //     }
    // }

    
    
}
