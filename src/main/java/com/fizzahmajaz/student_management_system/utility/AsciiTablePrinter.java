package com.fizzahmajaz.student_management_system.utility;

import java.util.List;

import com.fizzahmajaz.student_management_system.model.StudentModel;

import de.vandermeer.asciitable.AsciiTable;

public class AsciiTablePrinter {
     AsciiTable table = new AsciiTable();

    public void printStudents(List<StudentModel> students){

        //Method to print list of students
        if(students == null || students.isEmpty()){
            System.out.println("Students not found!");
            return;
        }else{
            
            table.addRule();
            table.addRow("ID", "Name", "Roll Number", "Email", "Course", "Grade");
            table.addRule();
            for(StudentModel s : students){
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

        }
    }

    //Method to print list of students
    public void printSingleStudent(StudentModel student) {
        if (student == null) {
            System.out.println("Students not found!");
        } else {
            
            table.addRule();
            table.addRow("ID", student.getId());

        }
    }

}
