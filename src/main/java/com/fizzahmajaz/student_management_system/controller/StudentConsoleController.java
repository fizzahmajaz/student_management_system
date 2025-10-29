package com.fizzahmajaz.student_management_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import com.fizzahmajaz.student_management_system.model.StudentModel;
import com.fizzahmajaz.student_management_system.services.StudentService;
import com.fizzahmajaz.student_management_system.utility.AsciiTablePrinter;
import com.fizzahmajaz.student_management_system.utility.ConsoleColors;
import com.fizzahmajaz.student_management_system.utility.utilityMethods;
import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
@Data
@AllArgsConstructor
public class StudentConsoleController implements CommandLineRunner {

    @Autowired
    private final StudentService studentService;
    private final utilityMethods utilityMethods;
    private final AsciiTablePrinter asciiTablePrinter;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {

        int choice;
        do {
            utilityMethods.printHeader("WELCOME TO STUDENT MANAGEMENT SYSTEM", "Developed by Fizzah M. Ajaz");
            System.out.println(ConsoleColors.YELLOW + "1. Add Student" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "2. View All Students" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "3. Search Student by ID" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "4. Search Student by Roll Number" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "5. Search Student by Name" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "6. Search Student by Email" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "7. Search Student by Grade" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "8. Search Student by Course" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "9. Update Student by ID" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "10. Update Student by Roll Number" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "11. Update Student by Email" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "12. Delete Student by Id" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "13. Delete Student by Roll Number" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "14. Delete Students by Email" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "15. Exit" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "Enter choice: " + ConsoleColors.RESET);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    ViewAllStudents();
                    break;

                case 3:
                    searchStudentByID();
                    break;

                case 4:
                    searchStudentByRollNumber();
                    break;

                case 5:
                    searchStudentByName();
                    break;

                case 6:
                    searchStudentByEmail();
                    break;

                case 7:
                    searchStudentByGrade();
                    break;

                case 8:
                    searchStudentByCourse();
                    break;

                case 9:
                    updateStudentById();
                    break;

                case 10:
                    updateStudentByRollNumber();
                    break;

                case 11:
                    updateStudentByEmail();
                    break;

                case 12:
                    deleteStudentById();
                    break;

                case 13:
                    deleteStudentByrollNumber();
                    break;

                case 14:
                    deleteStudentByEmail();
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 15);
    }

    // method to add students
    private void addStudent() {
        System.out.println(ConsoleColors.CYAN + "Enter name: " + ConsoleColors.RESET);
        String name = scanner.nextLine();

        System.out.println(ConsoleColors.CYAN + "Enter Roll Number: " + ConsoleColors.RESET);
        String rollNumber = scanner.nextLine();

        System.out.println(ConsoleColors.CYAN + "Enter email: " + ConsoleColors.RESET);
        String email = scanner.nextLine();

        System.out.println(ConsoleColors.CYAN + "Enter course: " + ConsoleColors.RESET);
        String course = scanner.nextLine();

        System.out.println(ConsoleColors.CYAN + "Enter grade: " + ConsoleColors.RESET);
        String grade = scanner.nextLine();

        StudentModel studentModel = new StudentModel(name, rollNumber, email, course, grade);
        studentService.addStudent(studentModel);
        System.out.println(ConsoleColors.GREEN + "Student added successfully!" + ConsoleColors.RESET);
        utilityMethods.pause();

    }

    // method to vew all students
    private void ViewAllStudents() {
        List<StudentModel> studentModels = studentService.getAllStudents();
        asciiTablePrinter.printStudents(studentModels);
        utilityMethods.pause();
    }

    // Get students by id
    private void searchStudentByID() {
        System.out.println(ConsoleColors.CYAN + "Enter student's ID:" + ConsoleColors.RESET);
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<StudentModel> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            asciiTablePrinter.printSingleStudent(student.get());
            
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }
        utilityMethods.pause();
    }

    // Get students by rollNumber
    private void searchStudentByRollNumber() {
        System.out.println(ConsoleColors.CYAN + "Enter student's Roll Number: " + ConsoleColors.RESET);
        String rollNumber = scanner.nextLine();
        Optional<StudentModel> student = studentService.getStudentByRollNumber(rollNumber);
        if (student.isPresent()) {
            asciiTablePrinter.printSingleStudent(student.get());
            
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }
        utilityMethods.pause();
    }

    // get student by email
    private void searchStudentByEmail() {
        System.out.println(ConsoleColors.CYAN + "Enter student's email: " + ConsoleColors.RESET);
        String email = scanner.nextLine();
        Optional<StudentModel> student = studentService.getStudentByEmail(email);
        if (student.isPresent()) {
            asciiTablePrinter.printSingleStudent(student.get());
            
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }
        utilityMethods.pause();
    }

    // get student by name
    private void searchStudentByName() {
        System.out.println(ConsoleColors.CYAN + "Enter Student's name: " + ConsoleColors.RESET);
        String name = scanner.nextLine();
        List<StudentModel> students = studentService.getStudentByName(name);
        if (!students.isEmpty()) {
            asciiTablePrinter.printStudents(students);
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }
        utilityMethods.pause();
    }

    // get student by grade
    private void searchStudentByGrade() {
        System.out.println(ConsoleColors.CYAN + "Enter Student's grade: " + ConsoleColors.RESET);
        String grade = scanner.nextLine();
        List<StudentModel> students = studentService.getStudentByGrade(grade);
        if (!students.isEmpty()) {
            asciiTablePrinter.printStudents(students);
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }
        utilityMethods.pause();

    }

    // get student by course
    private void searchStudentByCourse() {
        System.out.println(ConsoleColors.CYAN + "Enter Student's course: " + ConsoleColors.RESET);
        String course = scanner.nextLine();
        List<StudentModel> students = studentService.getStudentByCourse(course);
        if (!students.isEmpty()) {
            asciiTablePrinter.printStudents(students);
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }
        utilityMethods.pause();
    }

    // update student by id
    public void updateStudentById() {
        System.out.println(ConsoleColors.CYAN + "Enter the student's Id: " + ConsoleColors.RESET);
        Long id = scanner.nextLong();
        scanner.nextLine();

        StudentModel updatedData = utilityMethods.studentDataInput(id);
        StudentModel result = studentService.updateStudentById(id, updatedData);
        if (result != null) {
            System.out.println(ConsoleColors.GREEN + "Data updated successfully"  + ConsoleColors.RESET );
            System.out.println(result);
            utilityMethods.pause();
        } else {
            System.out.println(ConsoleColors.RED + "Student not found with id" + id + ConsoleColors.RESET);
        }
    }

    // update student by rollNumber
    public void updateStudentByRollNumber() {
        System.out.println(ConsoleColors.CYAN + "Enter the student's Roll Number: " + ConsoleColors.RESET);
        String rollNumber = scanner.nextLine();
        scanner.nextLine();

        StudentModel updatedData = utilityMethods.studentDataInput(null);
        StudentModel result = studentService.updateStudentByRollNumber(rollNumber, updatedData);
        if (result != null) {
            System.out.println(ConsoleColors.GREEN + "Data updated successfully"  + ConsoleColors.RESET);            System.out.println(result);
            utilityMethods.pause();
        } else {
            System.out.println(ConsoleColors.RED + "Student not found with Roll Number" + rollNumber + ConsoleColors.RESET);
        }
    }

    // update student by email
    public void updateStudentByEmail() {
        System.out.println(ConsoleColors.CYAN + "Enter the student's Email: " + ConsoleColors.RESET);
        String email = scanner.nextLine();
        scanner.nextLine();

        StudentModel updatedData = utilityMethods.studentDataInput(null);
        StudentModel result = studentService.updateStudentByEmail(email, updatedData);
        if (result != null) {
            System.out.println(ConsoleColors.GREEN + "Data updated successfully"  + ConsoleColors.RESET);            System.out.println(result);
            utilityMethods.pause();
        } else {
            System.out.println(ConsoleColors.RED + "Student not found with Email" + email + ConsoleColors.RESET);
        }
    }

    // delete student by id
    public void deleteStudentById() {
        System.out.println(ConsoleColors.CYAN + "Enter Student's id: " + ConsoleColors.RESET);
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean isDeleted = studentService.deleteStudentById(id);
        if (isDeleted) {
            System.out.println(ConsoleColors.GREEN + "Student deleted successfully" + ConsoleColors.RESET);
            utilityMethods.pause();
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }

    }

    // delete student by rollNumber
    public void deleteStudentByrollNumber() {
        System.out.println(ConsoleColors.CYAN + "Enter Student's Roll Number: " + ConsoleColors.RESET);
        String rollNumber = scanner.nextLine();
        boolean isDeleted = studentService.deleteStudentByRollNumber(rollNumber);
        if (isDeleted) {
            System.out.println(ConsoleColors.GREEN + "Student deleted successfully" + ConsoleColors.RESET);
            utilityMethods.pause();
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }

    }

    // delete student by Email
    public void deleteStudentByEmail() {
        System.out.println(ConsoleColors.CYAN + "Enter Student's email: " + ConsoleColors.RESET);
        String email = scanner.nextLine();
        boolean isDeleted = studentService.deleteStudentByEmail(email);
        if (isDeleted) {
            System.out.println(ConsoleColors.GREEN + "Student deleted successfully" + ConsoleColors.RESET);
            utilityMethods.pause();
        } else {
            System.out.println(ConsoleColors.RED + "Student not found" + ConsoleColors.RESET);
        }

    }

}
