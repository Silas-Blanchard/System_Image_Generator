package com.example.system_image_generator;

import java.util.ArrayList;

public class StarSystem {
    private ArrayList<Planemo> planets = new ArrayList<Planemo>();
    private String name;
    private String type;

    public ArrayList<Planemo> getPlanets() {
        return planets;
    }
    public String getName(){
        return name;
    }
    public String getType() {
        return type;
    }
    public Planemo getPlanet(int position){
        return planets.get(position);
    }
    public void editPlanet(int position, Planemo newPlanet){
        planets.set(position, newPlanet);
    }

    public void addPlanet(Planemo plany){
        this.planets.add(plany);
    }
    public void setName(String s){
        this.name = s;
    }
    public void setType(String type) {
        this.type = type;
    }

    public StarSystem (String name){
        this.name = name;
    }
}
