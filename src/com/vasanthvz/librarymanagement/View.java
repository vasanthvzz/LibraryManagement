package com.vasanthvz.librarymanagement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class View {
    public Scanner scanner = new Scanner(System.in);
    public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void showAlert(String alert){
        System.out.println("ALERT : "+alert);
    }
    public void showText(String text){
        System.out.println(text);
    }
    public void showTitle(String title){
        System.out.println("\n\n\t\t----"+title+"----\n\n");
    }
    public boolean tryAgain() {
        while (true){
            showText("Do you want to try again : ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("yes")) {
                return true;
            } else if (choice.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("\nInvalid choice, Please enter valid choice.\n");
                tryAgain();
            }
        }
    }
    public boolean checkForAddNewItems(String item) {
        while (true){
            System.out.println("\nDo you want to add more "+item+"s? \nType Yes/No");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("yes")) {
                return true;
            } else if (choice.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("\nInvalid choice, Please enter valid choice.\n");
            }
        }

    }


    public void onExit() {
        showText("----------------------EXITING-------------------------------");
    }
}
