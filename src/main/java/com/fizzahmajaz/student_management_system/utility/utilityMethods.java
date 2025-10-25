package com.fizzahmajaz.student_management_system.utility;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class utilityMethods {

    public void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public void pause(){
        System.out.println("\n Press Enter to continue...");
        new Scanner(System.in).nextLine();
    }

    public void printHeader(String title, String developer){
        clearScreen();
        System.out.println("\n========================================================");
        System.out.println("        " + title.toUpperCase());
        System.out.println("==========================================================");
        System.out.println("        " + developer.toUpperCase());
        System.out.println();

    }

    
    
}
