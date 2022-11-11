package com.africanbigcats;

import java.util.*;

/*
 * Menu class for the african big cat app
 */
public class Menu {

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
    public void executeFind(LinkedList<Panthera> catList) {

        // get the name
        System.out.print("Enter a name for the big cat you want to find: ");
        String name = input.nextLine();
        printLine();
        System.out.println();

        Iterator<Panthera> itr=catList.iterator();
        Boolean success = false;
        while(itr.hasNext()){
            Panthera cat = itr.next();
            String CatName = cat.name().toLowerCase();
            
            if (CatName.contains(name.toLowerCase())){
                if(success==false){
                    printLine();
                    System.out.println("Match Found for " + name + ": ");
                    printLine();
                }
                
                System.out.println(cat.toString());
                System.out.println();
                success = true;
            }
        }

        if (success == false){
            printLine();
            System.out.println("No Match Found for " + name + "!?");
            printLine();
            System.out.println();
        }
        

    }


}
