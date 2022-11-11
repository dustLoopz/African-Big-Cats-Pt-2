package com.africanbigcats;

import java.text.DecimalFormat;

/*
 * Panthera base class that simulates GPS information
 */
public class Lion extends Panthera {
    
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    // constructor
    public Lion(String name) {

        // call the super-class (parent) to instatiate it
        super(name);

        // initialize attributes
        this.setSpecies("lion");

    }

    // serializes attributes into a string
    @Override // override superclass method
    public String toString() {
        String s;

        // since the object is complex, we return a JSON formatted string
        s = "{ ";
        s += "name: " + name();
        s += ", ";
        s += "species: " + species();
        s += ", ";
        s += "longitude: " + df.format(longitude());
        s += ", ";
        s += "latitude: " + df.format(latitude());
        s += ", ";
        s += "weight: " + df.format(weight());
        s += ", ";
        s += "Speed: " + df.format(speed()) + " mph";
        s += ", ";
        s += "fur: " + fur();
        s += " }";

        return s;

    }

    public String fur() {
        return "mane";
    }

}