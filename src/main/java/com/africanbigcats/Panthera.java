package com.africanbigcats;

import java.util.Random;

/*
 * Panthera base class that simulates GPS information
 */
public class Panthera extends PantheraGPS {

    /*
        TIP:
        Students will need to add additional attributes and methods to complete this classes
        implementation.
     */
    // attributes
    private Integer weight;
    private Float speed;

    // constructor
    public Panthera(String name) {

        // call the super-class (parent) to instatiate it
        super(name);

        // initialize attributes
        this.setSpecies("panthera");

                
        //Generating random number for weight
        Random weightRandom = new Random();
        weight = weightRandom.nextInt(590)+10;

        //Generating random number for speed
        Random speedRandom = new Random();
        speed = speedRandom.nextFloat(50);

    }

    // serializes attributes into a string
    @Override // override superclass method
    public String toString() {
        String s;

        // since the object is complex, we return a JSON formatted string
        s = "{ ";
        s += "name: " + this.name();
        s += ", ";
        s += "species: " + this.species();
        s += ", ";
        s += "longitude: " + this.longitude();
        s += ", ";
        s += "latitude: " + this.latitude();
        s += " }";
        s += "latitude: " + this.weight;
        s += " }";

        return s;

    }

    // Weight of the panthera
    public Integer weight() {
        return weight;
    }

     // Weight of the panthera
     public Float speed() {
        return speed;
    }

    // Method Prints "Rrrrrrrroooooaaaarrrr"
    public void roar() {
        System.out.println("Rrrrrrrroooooaaaarrrr" );
    }

    public void checkIn() {

        //Generating random number for weight
        Random weightRandom = new Random();
        this.weight += weightRandom.nextInt(20)-10;;

        //Updating Speed
        Random speedRandom = new Random();
        speed = speedRandom.nextFloat(50);
        
        
    }


}
