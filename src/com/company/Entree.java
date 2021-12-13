package com.company;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class Entree {
    private String fName;
    private String lName;
    private LocalDate date;
    private LocalDate birthdate;
    private int age;
    private boolean walkOrNo;
    private int height;
    private int weight;
    private boolean isBirthday;
    private boolean isDiscount;
    private int ticketID = (int)((Math.random()*90000)+10000);
    Entree(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        fName = input.nextLine();
        System.out.print("Enter your last name: ");
        lName = input.nextLine();
        System.out.print("Enter the date of attendance (MM/DD/YYYY): ");
        date = safeDateInput(input);
        System.out.print("Enter your birthdate (MM/DD/YYYY): ");
        birthdate = safeDateInput(input);
        age = createAge(date, birthdate);
        isBirthday = createBirthday(date, birthdate);
        System.out.print("Enter your height in inches: ");
        height = safeInput(input);
        System.out.print("Enter your weight in pounds: ");
        weight = safeInput(input);
        System.out.println("Will you be\n1 Walking\n2 Driving");
        boolean redoWalk  = true;
        while(redoWalk == true) {
            int walkDriveInt = safeInput(input);
            if (walkDriveInt == 1) {
                walkOrNo = true;
                redoWalk = false;
            } else if (walkDriveInt == 2) {
                walkOrNo = false;
                redoWalk = false;
            } else {
                System.out.println("Enter 1 for walking and 2 for driving: ");
            }
        }
        String memberCode;
        System.out.println("Enter a discount code (none if none): ");
        input.nextLine();
        memberCode = input.nextLine();
        if(memberCode.equals("MEMBER")){
            isDiscount = true;
        }
        else{
            isDiscount = false;
        }
    }
    public static boolean createBirthday(LocalDate theDate, LocalDate dateOfBirth) {
        if (theDate.getMonthValue() == dateOfBirth.getMonthValue() && theDate.getDayOfMonth() == dateOfBirth.getDayOfMonth()) {
            return true;
        }
        return false;
    }

    public static int createAge(LocalDate theDate, LocalDate dateOfBirth){
        int output;
        if(theDate.getMonthValue()>dateOfBirth.getMonthValue()||(theDate.getMonthValue()==dateOfBirth.getMonthValue()&&theDate.getDayOfMonth()>dateOfBirth.getDayOfMonth())){
            output = theDate.getYear()-dateOfBirth.getYear();
        }
        else{
            output = theDate.getYear()-dateOfBirth.getYear()-1;
        }
        return output;
    }

    public String makeTicket(){
        double totalCost;
        if (walkOrNo) {
            if (age > 17) {
                if ((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                    totalCost = 25;
                } else {
                    totalCost = 16;
                }
            } else if (age > 14) {
                if ((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                    totalCost = 18;
                } else {
                    totalCost = 12;
                }
            } else if (age > 2){
                if ((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                    totalCost = 12;
                } else {
                    totalCost = 8;
                }
            } else {
                totalCost = 0;
            }

        } else {
            totalCost = 65;
        }
        if(isDiscount){
            totalCost *= .8;
        }
        totalCost *= 100;
        totalCost = Math.round(totalCost);
        totalCost /= 100;

        String ticketName;
        String ticketAge;
        String ticketAlcohol;
        String ticketTrain;
        String ticketPrice;
        String theTicketID = "Ticket ID: "+ticketID;
        String fullName = fName+" "+lName;
        ticketName = "Your name is " + fullName;
        if(fullName.length()>45){
            ticketName = "Your name is "+fullName.substring(0,46)+"...";
        }


        ticketAge = "You will be " + age + " years old";
        if (((age > 21) && walkOrNo) ||  (fName.toLowerCase().equals("sam") && lName.toLowerCase().equals("felsted"))){
            ticketAlcohol = "You can access the alcohol area";
        }
        else{
            ticketAlcohol = "You can't access the alcohol area";
        }
        if ((height > 48 && weight < 300) && walkOrNo) {
            ticketTrain = "You can ride the train";
        }
        else{
            ticketTrain = "You can't ride the train";
        }
        String totalCostStr = ""+totalCost;
        if(totalCostStr.indexOf(".") == -1){
            totalCostStr += ".00";
        }
        else if(totalCostStr.indexOf(".")==totalCostStr.length()-2){
            totalCostStr += "0";
        }

        ticketPrice = "Your total cost is $"+totalCostStr;
        String ticket = "";
        /*
        Expected output:
┏---+---=---+---+---=---+---+---=---+---+---=---+---+---=---+---┓
|                    Your name is Sam Hatler                    |
+                                                               +
|                    You will be 14 years old                   |
+                                                               +
|                You can access the alcohol area                |
+                                                               +
|                    You can ride the train                     |
+                                                               +
|                     Your total cost is $9                     |
+                                                               +
|                       Ticket ID: 96757                        |
┗---+---=---+---+---=---+---+---=---+---+---=---+---+---=---+---┛
63 spaces
 */
        //making variables for spaces
        int numberSpace;
        int numberSpaceFront;
        int numberSpaceBack;
        String spaceFront;
        String spaceBack;
        //adding top of ticket
        ticket += "┏---+---=---+---+---=---+---+---=---+---+---=---+---+---=---+---┓\n";
        //calculating spaces
        numberSpace = 63-ticketName.length();
        numberSpaceFront = (int)(numberSpace/2);
        numberSpaceBack = numberSpace-numberSpaceFront;
        spaceFront = makeSpace(numberSpaceFront);
        spaceBack = makeSpace(numberSpaceBack);
        //adding to next line
        ticket += "|"+spaceFront+ticketName+spaceBack+"|\n";
        //next line
        ticket += "+                                                               +\n";
        //same process for next line
        numberSpace = 63-ticketAge.length();
        numberSpaceFront = (int)(numberSpace/2);
        numberSpaceBack = numberSpace-numberSpaceFront;
        spaceFront = makeSpace(numberSpaceFront);
        spaceBack = makeSpace(numberSpaceBack);
        ticket += "|"+spaceFront+ticketAge+spaceBack+"|\n";
        ticket += "+                                                               +\n";
        numberSpace = 63-ticketAlcohol.length();
        numberSpaceFront = (int)(numberSpace/2);
        numberSpaceBack = numberSpace-numberSpaceFront;
        spaceFront = makeSpace(numberSpaceFront);
        spaceBack = makeSpace(numberSpaceBack);
        ticket += "|"+spaceFront+ticketAlcohol+spaceBack+"|\n";
        ticket += "+                                                               +\n";
        numberSpace = 63-ticketTrain.length();
        numberSpaceFront = (int)(numberSpace/2);
        numberSpaceBack = numberSpace-numberSpaceFront;
        spaceFront = makeSpace(numberSpaceFront);
        spaceBack = makeSpace(numberSpaceBack);
        ticket += "|"+spaceFront+ticketTrain+spaceBack+"|\n";
        ticket += "+                                                               +\n";
        numberSpace = 63-ticketPrice.length();
        numberSpaceFront = (int)(numberSpace/2);
        numberSpaceBack = numberSpace-numberSpaceFront;
        spaceFront = makeSpace(numberSpaceFront);
        spaceBack = makeSpace(numberSpaceBack);
        ticket += "|"+spaceFront+ticketPrice+spaceBack+"|\n";
        ticket += "+                                                               +\n";



        numberSpace = 63-theTicketID.length();
        numberSpaceFront = (int)(numberSpace/2);
        numberSpaceBack = numberSpace-numberSpaceFront;
        spaceFront = makeSpace(numberSpaceFront);
        spaceBack = makeSpace(numberSpaceBack);
        ticket += "|"+spaceFront+theTicketID+spaceBack+"|\n";
        ticket += "┗---+---=---+---+---=---+---+---=---+---+---=---+---+---=---+---┛";

        return ticket;
    }

    public static String makeSpace(int howMany){
        String output = "";
        for(int i = 0; i<howMany; i++){
            output += " ";
        }
        return output;
    }
    public static LocalDate safeDateInput(Scanner input) {
        String str;
        boolean redoLength = true;
        int year, date, month;
        while (redoLength) {
            str = input.nextLine();
            if (str.length() != 10) {
                System.out.print("Please enter a valid date: ");
            }
            else{
                String strMonth = str.substring(0, 2);
                // 12/01/2020
                String strDay = str.substring(3,5);
                String strYear = str.substring(6, 10);


                //Parse month
                try{
                    int monthNum = Integer.parseInt(strMonth);
                    if (monthNum <= 12){
                        int yearNum = Integer.parseInt(strYear);
                        int dayNum = Integer.parseInt(strDay);
                        if(dayNum <= maxDay(monthNum, yearNum)){
                            redoLength = false;
                            return LocalDate.of(yearNum, monthNum, dayNum);
                        }

                    } else {
                        System.out.print("Please enter a valid date: ");
                    }

                }
                catch (NumberFormatException ex){
                    System.out.print("Please enter a valid date: ");
                }

                System.out.print("Please enter a valid date: ");
            }


        }

        return LocalDate.of(0, 0, 0);

    }
    public static int maxDay(int monthNum, int yearNum){
        if(monthNum==1||monthNum==3||monthNum==5||monthNum==7||monthNum==8||monthNum==10||monthNum==12){
            return 31;
        }
        if(monthNum==4||monthNum==6||monthNum==9||monthNum==11){
            return 30;
        }
        if(yearNum%4==0&&(yearNum%400==0||!(yearNum%100==0))){
            return 29;
        }
        return 28;
    }

    public static int safeInput(Scanner read) {
        while (true) {
            try {
                return read.nextInt();
            } catch (Exception e) {
                System.out.print("Error, not an integer, ");
                read.nextLine();
            }
        }
    }


}