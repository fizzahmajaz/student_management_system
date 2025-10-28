package com.fizzahmajaz.student_management_system.utility;

import java.util.List;

import org.springframework.stereotype.Component;
import com.fizzahmajaz.student_management_system.model.StudentModel;
import de.vandermeer.asciitable.AsciiTable;

@Component
public class AsciiTablePrinter {

    public void printStudents(List<StudentModel> students) {
        // Method to print list of students
        if (students == null || students.isEmpty()) {
            System.out.println("Students not found!");
            return;
        }

        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("ID", "Name", "Roll Number", "Email", "Course", "Grade");
        table.addRule();
        for (StudentModel s : students) {
            table.addRow(
                    s.getId(),
                    s.getName(),
                    s.getRollNumber(),
                    s.getEmail(),
                    s.getCourse(),
                    s.getGrade()

            );

            table.addRule();

        }
        String renderedTable = table.render();
        System.out.println(renderedTable);
    }

    // Method to print list of students
    public void printSingleStudent(StudentModel student) {

        if (student == null) {
            System.out.println("Students not found!");
        }

        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("ID", student.getId());
        table.addRow("Name", student.getName());
        table.addRow("Roll Number", student.getRollNumber());
        table.addRow("Email", student.getEmail());
        table.addRow("Course", student.getCourse());
        table.addRow("Grade", student.getGrade());

        String renderedTable = table.render();
        System.out.println(renderedTable);

    }

}
