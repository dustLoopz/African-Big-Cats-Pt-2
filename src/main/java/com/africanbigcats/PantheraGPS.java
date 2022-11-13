package com.africanbigcats;

import java.util.Random;

/*
 * Panthera base class that simulates GPS information
 */
public class PantheraGPS {

    // constants
    protected final float minSpeed = 0f;
    protected final float maxSpeed = 50.0f;

    // attributes
    private String name;
    private String species;

    private Float longitude;
    private Float latitude;

    private Random longitudeRandom;
    private Random latitudeRandom;

    // constructor
    public PantheraGPS(String name) {

        // initialize attributes
        this.name = name;
        this.species = "pantheraGPS";

        //Limits of Latitude in Africa  +- 35
        //Limits of Longitude in Africa  -18, +55

        // seed the random number generators for repeatable results
        this.longitudeRandom = new Random();
        this.longitudeRandom.setSeed(this.seed(name + "longitude"));
        this.latitudeRandom = new Random();
        this.latitudeRandom.setSeed(this.seed(name + "latitude"));

        // move the panthera into it's initial position
        this.longitude = longitudeRandom.nextFloat(73) - 18;
        this.latitude = latitudeRandom.nextFloat(70) - 35;

    }

    // serializes attributes into a string
    public String toString() {
        String s;

        // since the object is complex, we return a JSON formatted string
        s = "{ ";
        s += "name: " + name;
        s += ", ";
        s += "species: " + this.species();
        s += ", ";
        s += "longitude: " + this.longitude();
        s += ", ";
        s += "latitude: " + this.latitude();
        s += " }";

        return s;

    }

    // getters
    public String name() {
        return this.name;
    }

    public String species() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    // make a seed, based on the name
    private Integer seed(String s) {
        Integer seed = 0;

        for (Integer i = 0; i < s.length() ; i++) {
            char ch = s.charAt(i);
            seed += (int) ch;
        }

        return seed;
    }

    public void move() {
        this.longitude += longitudeRandom.nextFloat() * maxSpeed;
        this.latitude += latitudeRandom.nextFloat() * maxSpeed;
    }

    // longitude of the panthera
    public Float longitude() {
        return longitude;
    }

    // Mutator for Longitude
    public void longitude(Float newLongitude) {
        this.longitude = newLongitude; 
    }

    // latitude of the panthera
    public Float latitude() {
        return latitude;
    }

    // Mutator for Latitude
    public void latitude(Float newLatitude) {
        this.latitude = newLatitude; 
    }


}
