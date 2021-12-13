package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Entree person = new Entree();
        boolean redo = true;
        String again = "";
        int ticketOrNo;
        boolean question = true;
        while(redo) {
            if(question) {
                System.out.println("Would you like to print your ticket" + again + "?\n1 Yes\n2 No");
            }
            question = true;
            ticketOrNo = input.nextInt();
            if(ticketOrNo == 1) {
                System.out.println(person.makeTicket()+"\n");
                again = " again";
            }
            else if(ticketOrNo == 2){
                System.out.println("No ticket");
                redo = false;
            }
            else{
                System.out.print("Enter 1 for yes or 2 for no: ");
                question = false;
            }
        }
    }
}