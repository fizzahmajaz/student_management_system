package com.fizzahmajaz.student_management_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import com.fizzahmajaz.student_management_system.model.StudentModel;
import com.fizzahmajaz.student_management_system.services.StudentService;
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
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {

        int choice;
        do {
            utilityMethods.printHeader("WELCOME TO STUDENT MANAGEMENT SYSTEM", "Developed by Fizzah M. Ajaz");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Roll Number");
            System.out.println("5. Search Student by Name");
            System.out.println("6. Search Student by Email");
            System.out.println("7. Search Student by Grade");
            System.out.println("8. Search Student by Course");
            System.out.println("9. Update Student by ID");
            System.out.println("10. Update Student by Roll Number");
            System.out.println("11. Update Student by Email");
            System.out.println("12. Delete Student by Id");
            System.out.println("13. Delete Student by Roll Number");
            System.out.println("14. Delete Students by Email");
            System.out.println("15. Exit");
            System.out.println("Enter choice: ");
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
        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter course: ");
        String course = scanner.nextLine();

        System.out.println("Enter grade: ");
        String grade = scanner.nextLine();

        StudentModel studentModel = new StudentModel(name, rollNumber, email, course, grade);
        studentService.addStudent(studentModel);
        System.out.println("Student added successfully!");
        utilityMethods.pause();

    }

    // method to vew all students
    private void ViewAllStudents() {
        List<StudentModel> studentModels = studentService.getAllStudents();
        System.out.println("\nID | Name | Roll Number | Email | Course | Grade");
        studentModels.forEach(System.out::println);
    }

    // Get students by id
    private void searchStudentByID() {
        System.out.println("Enter student's ID:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<StudentModel> studentModel = studentService.getStudentById(id);
        studentModel.ifPresentOrElse(System.out::println, () -> System.out.println("Student not found"));
    }

    // Get students by rollNumber
    private void searchStudentByRollNumber() {
        System.out.println("Enter student's Roll Number: ");
        String rollNumber = scanner.nextLine();
        Optional<StudentModel> student = studentService.getStudentByRollNumber(rollNumber);
        if (student.isPresent()) {
            System.err.println(student.get());
            utilityMethods.pause();
        } else {
            System.out.println("Student not found");
        }
    }

    // get student by email
    private void searchStudentByEmail() {
        System.out.println("Enter student's email: ");
        String email = scanner.nextLine();
        Optional<StudentModel> student = studentService.getStudentByEmail(email);
        if (student.isPresent()) {
            System.err.println(student.get());
            utilityMethods.pause();
        } else {
            System.out.println("Student not found");
        }
    }

    // get student by name
    private void searchStudentByName() {
        System.out.println("Enter Student's name: ");
        String name = scanner.nextLine();
        List<StudentModel> students = studentService.getStudentByName(name);
        if (!students.isEmpty()) {
            System.out.println("Students Found!");
            for (StudentModel s : students) {
                System.out.printf("%-5d %-15s %-15s %-25s %-10s %-5s\n",
                        s.getId(), s.getName(), s.getRollNumber(), s.getEmail(), s.getCourse(), s.getGrade());
            }
            utilityMethods.pause();
        }

        else

        {
            System.out.println("Students not found");
        }
    }

    // get student by grade
    private void searchStudentByGrade() {
        System.out.println("Enter Student's grade: ");
        String grade = scanner.nextLine();
        List<StudentModel> students = studentService.getStudentByGrade(grade);
        if (!students.isEmpty()) {
            System.out.println("Students Found!");
            for (StudentModel s : students) {
                System.out.printf("%-5d %-15s %-15s %-25s %-10s %-5s\n",
                        s.getId(), s.getName(), s.getRollNumber(), s.getEmail(), s.getCourse(), s.getGrade());
            }
            utilityMethods.pause();
        } else {
            System.out.println("Students not found");
        }

    }

    // get student by course
    private void searchStudentByCourse() {
        System.out.println("Enter Student's course: ");
        String course = scanner.nextLine();
        List<StudentModel> students = studentService.getStudentByCourse(course);
        if (!students.isEmpty()) {
            System.out.println("Students Found!");
            for (StudentModel s : students) {
                System.out.printf("%-5d %-15s %-15s %-25s %-10s %-5s\n",
                        s.getId(), s.getName(), s.getRollNumber(), s.getEmail(), s.getCourse(), s.getGrade());
            }
            utilityMethods.pause();
        }

        else {
            System.out.println("Students not found");
        }
    }

    // update student by id
    public void updateStudentById() {
        System.out.println("Enter the student's Id: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

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

        StudentModel updatedData = new StudentModel();
        updatedData.setId(id);
        updatedData.setName(name);
        updatedData.setRollNumber(rollNumber);
        updatedData.setEmail(email);
        updatedData.setCourse(course);
        updatedData.setGrade(grade);

        StudentModel result = studentService.updateStudentById(id, updatedData);
        if (result != null) {
            System.out.println("Data updated successfully");
            System.out.println(result);
            utilityMethods.pause();
        } else {
            System.out.println("Student not found with id" + id);
        }
    }

    // update student by rollNumber
    public void updateStudentByRollNumber() {
        System.out.println("Enter the student's Id: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

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

        StudentModel updatedData = new StudentModel();
        updatedData.setId(id);
        updatedData.setName(name);
        updatedData.setRollNumber(rollNumber);
        updatedData.setEmail(email);
        updatedData.setCourse(course);
        updatedData.setGrade(grade);

        StudentModel result = studentService.updateStudentByRollNumber(rollNumber, updatedData);
        if (result != null) {
            System.out.println("Data updated successfully");
            System.out.println(result);
            utilityMethods.pause();
        } else {
            System.out.println("Student not found with id" + id);
        }
    }

    // update student by email
    public void updateStudentByEmail() {
        System.out.println("Enter the student's Id: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

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

        StudentModel updatedData = new StudentModel();
        updatedData.setId(id);
        updatedData.setName(name);
        updatedData.setRollNumber(rollNumber);
        updatedData.setEmail(email);
        updatedData.setCourse(course);
        updatedData.setGrade(grade);

        StudentModel result = studentService.updateStudentByEmail(email, updatedData);
        if (result != null) {
            System.out.println("Data updated successfully");
            System.out.println(result);
            utilityMethods.pause();
        } else {
            System.out.println("Student not found with id" + id);
        }
    }

    // delete student by id
    public void deleteStudentById() {
        System.out.println("Enter Student's id: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        boolean isDeleted = studentService.deleteStudentById(id);
        if (isDeleted) {
            System.out.println("Student deleted successfully");
            utilityMethods.pause();
        } else {
            System.out.println("Student not found");
        }

    }

    // delete student by rollNumber
    public void deleteStudentByrollNumber() {
        System.out.println("Enter Student's Roll Number: ");
        String rollNumber = scanner.nextLine();
        boolean isDeleted = studentService.deleteStudentByRollNumber(rollNumber);
        if (isDeleted) {
            System.out.println("Student deleted successfully");
            utilityMethods.pause();
        } else {
            System.out.println("Student not found");
        }

    }

    // delete student by Email
    public void deleteStudentByEmail() {
        System.out.println("Enter Student's email: ");
        String email = scanner.nextLine();
        boolean isDeleted = studentService.deleteStudentByEmail(email);
        if (isDeleted) {
            System.out.println("Student deleted successfully");
            utilityMethods.pause();
        } else {
            System.out.println("Student not found");
        }

    }

}
