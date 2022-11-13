package com.africanbigcats;

import java.util.*;
import java.text.DecimalFormat;

/*
 * Menu class for the african big cat app
 */
public class Menu {

    //Decimal Formatting
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // attributes
    private Scanner input;

    // constructor
    public Menu() {

        // initialize attributes
        this.input = new Scanner(System.in);

    }

    // prints the menu
    public void print() {

        //Print User Menu with Options

        printLine();
        System.out.println("African Big Cats App");
        printLine();

        printCommand('c',"[C]reates a big cat");
        printCommand('d',"[D]eletes a big cat");
        printCommand('f',"[F]inds a big cat");
        printCommand('l',"[L]ists all big cats");
        printCommand('w',"[W]arning Report");
        printCommand('r',"[R]isk Report");
        printCommand('q',"[Q]uits");

        printLine();

    }

    private static void printLine() {
        System.out.println("----------------------------------------------------------" );
    }

    private static void printCommand(Character command, String desc) {
        System.out.printf("%s\t%s\n", command, desc);
    }

    // get first character from input
    public Character getCommand() {

        Character command = '_';

        String rawInput = input.nextLine();
        
        if (rawInput.length() > 0) {
            rawInput = rawInput.toLowerCase();
            command = rawInput.charAt(0);
        }

        return command;

    }

    // command switch
    public Boolean executeCommand(Character command, LinkedList<Panthera> catList) {

        Boolean success = true;

        switch(command) {

            case 'c':
                executeCreate(catList);
                break;
            case 'd':
                executeDelete(catList);
                break;
            case 'f':
                executeFind(catList);
                break;
            case 'l':
                executeList(catList);
                break;
            case 'w':
                warningReport(catList, input);
                break;
            case 'r':
                riskReport(catList, input);
                break;
            case 'q':
                executeQuit();
                break;

            default:
                System.out.println("ERROR: Unknown commmand");
                success = false;
          }

        return success;
    }

    // update the position of all the cats
    public void update(LinkedList<Panthera> catList) {

        // update by moving all the cats
        for (Panthera cat: catList) {
            cat.move();
            cat.checkIn();
        }

    }

    // quit the app
    public void executeQuit() {

        // close the scannner
        input.close();

        System.out.println();
        printLine();
        System.out.println("Thank you for using the African Big Cats App!");
        printLine();
        System.out.println();

    }

    public Panthera getNewCat(String name) {
        
        Boolean success = false;
        Panthera result = new Panthera(name); 

        while (success == false){
            System.out.print("What kind of cat is this? Available cats (1. Tiger, 2. Lion, 3. Jaguar): ");
            String type = input.nextLine();
            System.out.println();

            switch(type) {
                case "1":
                case "tiger":
                case "Tiger":
                    result = new Tiger(name);
                    success = true;
                    break;
                case "2":
                case "lion":
                case "Lion":
                    result = new Lion(name);
                    success = true;
                    break;
                case "3":
                case "jaguar":
                case "Jaguar":
                    result = new Jaguar(name);
                    success = true;
                    break;
                default:
                    System.out.println("ERROR: Invalid Option");
                    success = false;
              }
        }
        
        return result;

    }

    // create a cat, if it's unique
    public void executeCreate(LinkedList<Panthera> catList) {

        // get the name
        System.out.println();
        System.out.print("Enter a name for the big cat to create: ");
        String name = input.nextLine();

        //Checking for Duplicate Name
        Iterator<Panthera> itr=catList.iterator();
        Boolean duplicate = false;
        while(itr.hasNext()){
            Panthera cat = itr.next();
            String CatName = cat.name().toLowerCase();
            
            if (CatName.equals(name.toLowerCase())){
                System.out.println("This cat, " + name + ", is already in the list.");
                System.out.println("Please try a new cat.");
                System.out.println();
                duplicate = true;
            }
        }

        if (duplicate == false){
            Panthera cat = getNewCat(name);
            catList.add(cat);
        }
    }

    // list all big cats 
    public void executeList(LinkedList<Panthera> catList) {

        System.out.println();
        printLine();
        System.out.println("African Big Cat List");
        printLine();

        Panthera cat;
        if (catList.size() > 0) {
            for (Integer i = 0; i < catList.size(); i++) {
                cat = catList.get(i);
                System.out.println(cat);
            }
        } else {
            System.out.println("There are no African Big Cats. :(");
        }

        System.out.println();

    }

    // Delete Cat 
    public void executeDelete(LinkedList<Panthera> catList) {
        
        // get the name
        System.out.println();
        System.out.print("Enter a name for the big cat to delete: ");
        String name = input.nextLine();
        System.out.println();
        
        Iterator<Panthera> itr=catList.iterator();
        boolean success = false; 

        while(itr.hasNext()){
            Panthera cat = itr.next();
            String CatName = cat.name();
           
            if (CatName.equals(name)){
                itr.remove();
                success = true;
                printLine();
                System.out.println("Cat named " + name + " deleted.");
                printLine();
                System.out.println();
            }
        }

        if (success == false){
            printLine();
            System.out.println("No Match Found for " + name + "!?");
            printLine();
            System.out.println();
        }
    }

    // Find a big cat
    // Input: Cat name
    // Ouput: Prints cat if found and alerts user if cat doesn't exist
    public void executeFind(LinkedList<Panthera> catList) {
        // get the name
        System.out.print("Enter a name for the big cat you want to find: ");
        String name = input.nextLine();
        printLine();
        System.out.println();
        
        executeFind(catList, name);
    }

    // Find a big cat
    // Return Panthera Object
    public Panthera executeFind(LinkedList<Panthera> catList, String catName) {
        Panthera cat = new Panthera("null");

        Iterator<Panthera> itr=catList.iterator();
        Boolean success = false;
        while(itr.hasNext()){
            cat = itr.next();
            String CatName = cat.name().toLowerCase();
            
            if (CatName.contains(catName.toLowerCase())){
                if(success==false){
                    printLine();
                    System.out.println("Match Found for " + catName + ": ");
                    printLine();
                }
                
                System.out.println(cat.toString());
                System.out.println();
                success = true;
                return cat;
                
            }
        }

        if (success == false){
            printLine();
            System.out.println("No Match Found for " + catName + "!?");
            printLine();
            System.out.println();
            cat = null;
        }

        return cat; 
    }
    
    // Risk Report
    // Prints the distance between two selected cats
    public void riskReport(LinkedList<Panthera> catList, Scanner input) {
        //Print List of Valid Cat Names

        // get the cat 1 name
        System.out.print("Enter a name for the first big cat: ");
        String cat1 = input.nextLine();
        printLine();
        System.out.println();

        // get the cat 2 name
        System.out.print("Enter a name for the second big cat: ");
        String cat2 = input.nextLine();
        printLine();
        System.out.println();

        if (cat1 != "" && cat2 != ""){

            //Get Cat 1 and Cat 2
            Panthera catObj1 = executeFind(catList, cat1);
            Panthera catObj2 = executeFind(catList, cat2);

            if (catObj1 != null && catObj2 != null){

                System.out.print("Cat Names: " + cat1 + " and " + cat2 + "\n");

                //Get Longitude
                Float longitude1 = catObj1.longitude();
                Float longitude2 = catObj2.longitude();

                //Get Latitude
                Float latitude1 = catObj1.latitude();
                Float latitude2 = catObj2.latitude();         

                //Calculate Distance using formula
                Float latDelta = (float) Math.pow((longitude2 - longitude1), 2);
                Float longDelta = (float) Math.pow((latitude2 - latitude1), 2);
                
                Float distance =(float) Math.sqrt(latDelta + longDelta);

                //Print Results
                System.out.print("Distance Between Cats = " + df.format(distance) + "\n");
            } else{
                System.out.print("Not all cats found. Risk report failed!\n\n");
            }
            
        } else{

            //Invalid Names Error
            System.out.print("Invalid Name. Try again.\n");

        }
    }

    // Risk Report
    // Input: User location for longitude and latitude
    // Prints information on the nearest cat to user location
    public void warningReport(LinkedList<Panthera> catList, Scanner input) {
        //Get User Input for Latitude
         System.out.print("Enter your latitude(+90,-90): ");
         Float userLatitude = input.nextFloat();
         printLine();
         System.out.println();
 
        //Get User Input for Longitude
        System.out.print("Enter your longitude(+180,-180): ");
        Float userLongitude = input.nextFloat();
        printLine();
        System.out.println();

        //Limits of Longitude +- 180
        if(userLongitude > 180){
            System.out.print("Maximum Longitude Exceeded. Its out of this world! Set to 180.\n");
            userLongitude = 180f;
        } else if (userLongitude < -180){
            System.out.print("Maximum Longitude Exceeded. Its out of this world! Set to -180.\n");
            userLongitude = -180f;
        }
        
        //Limits of Latitude +- 90
        if(userLatitude > 90){
            System.out.print("Maximum Latitude Exceeded. Its out of this world! Set to 90.\n");
            userLatitude=90f;
        } else if (userLatitude < -90){
            System.out.print("Maximum Latitude Exceeded. Its out of this world! Set to -90.\n");
            userLatitude=-90f;
        }

        //Clear Buffer
        input.nextLine();

        //Go through list of cats and calculate Distance to given point
        Panthera cat = new Panthera("null");
        Panthera closestCat = new Panthera("null");

        Iterator<Panthera> itr=catList.iterator();

        Float closestDistance = 1000000f; 
        
        while(itr.hasNext()){
            cat = itr.next();
            Float catLatitude = cat.latitude();
            Float catLongitude = cat.longitude();

            //Calculate Distance using formula
            Float latDelta = (float) Math.pow((userLatitude - catLatitude), 2);
            Float longDelta = (float) Math.pow((userLongitude - catLongitude), 2);
            
            Float distance =(float) Math.sqrt(latDelta + longDelta);

            if (distance < closestDistance){
                closestDistance = distance; 
                closestCat = cat;
            }
        }

        if (closestCat.name() != "null"){
            System.out.print("Warning Report:\n");
            printLine();
            System.out.print("Closest cat: " + closestCat.name() + " is " + df.format(closestDistance) + " away.\n\n");
        }

    }


}
