package com.africanbigcats;

import java.text.DecimalFormat;

/*
 * Panthera base class that simulates GPS information
 */
public class Jaguar extends Panthera {

    private static final DecimalFormat df = new DecimalFormat("0.00");

	//attributes
	private boolean sleepsInTrees = true; 
    // constructor
    public Jaguar(String name) {

        // call the super-class (parent) to instatiate it
        super(name);

        // initialize attributes
        this.setSpecies("jaguar");

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
		s += ", ";
		s += "Sleeps in Trees: " + sleepsInTrees();
        s += " }";

        return s;

    }
	
	public boolean sleepsInTrees() {
        return this.sleepsInTrees;
    }
	
    public String fur() {
        return "spots";
    }

}