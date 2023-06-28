package com.example.system_image_generator;

import java.util.ArrayList;

public class Planemo {
    private String name;
    private ArrayList<Planemo> moons;
    private double size;
    private double radius;

    private double angle;

    public void setName(String name) {
        this.name = name;
    }

    public void addMoon(Planemo moon) {
        this.moons.add(moon);
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setAngle(double angle){
        this.angle = Math.toRadians(angle);
    }

    public double getAngle() {
        return angle;
    }

    public Double getRadius(){
        return this.radius;
    }

    public ArrayList<Planemo> getMoons() {
        return moons;
    }

    public double getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public Planemo (String name, Double size, Double radius){
        this.name = name;
        this.size = size;
        this.radius = radius;
    }
}
