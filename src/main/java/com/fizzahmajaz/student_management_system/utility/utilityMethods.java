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
        System.out.println(ConsoleColors.PURPLE + "\n Press Enter to continue..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    //For printing the header
    public void printHeader(String title, String developer){
        clearScreen();
        System.out.println(ConsoleColors.BOLD_WHITE + "\n==============================================================================================================================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BOLD_PURPLE + "                        " + title.toUpperCase() + ConsoleColors.RESET);
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(ConsoleColors.PURPLE + "                             " + developer.toUpperCase() + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BOLD_WHITE + "=================================================================================================================================" + ConsoleColors.RESET);
        
        System.out.println();

    }

    //For taking the student data
    public StudentModel studentDataInput(Long id){

        System.out.println(ConsoleColors.CYAN + "Enter the student's name: " + ConsoleColors.RESET);
        String name = scanner.nextLine();

        System.out.println(ConsoleColors.CYAN + "Enter the student's Roll Number: " + ConsoleColors.RESET);
        String rollNumber = scanner.nextLine();

        System.out.println(ConsoleColors.CYAN + "Enter the student's email: " + ConsoleColors.RESET);
        String email = scanner.nextLine();

        System.out.println(ConsoleColors.CYAN + "Enter the student's course: " + ConsoleColors.RESET);
        String course = scanner.nextLine();

        System.out.println(ConsoleColors.CYAN + "Enter the student's grade: " + ConsoleColors.RESET);
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
