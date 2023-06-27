package com.example.system_image_generator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Artist {
    private StarSystem sys;
    Planemo thalsiedeln;
    public Artist(){
        sys = new StarSystem("Arlioux"); //Making a default system used for testing purposes
        thalsiedeln = new Planemo("Thalsiedeln", 10, 100); //size should be in earth radii and radius is in AU
        thalsiedeln.setAngle(180);
        sys.addPlanet(thalsiedeln); //It has one planet

        //Another planet!
        Planemo belt = new Planemo("Belt", 10, 200); //size should be in earth radii and radius is in AU
        belt.setAngle(40);
        sys.addPlanet(belt); //It has one planet
    }

    public Scene draw() throws FileNotFoundException {
        GridPane grid = new GridPane();

        //all of this makes the background image ):
        FileInputStream inputstream = new FileInputStream("src/assets/LumiraBackground.png");
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(800);
        imageView.setFitWidth(800);

        //the ground we will add (planets and stuff)
        Group g = new Group();

        //adding the star
        Circle star = new Circle(0, 0, 30);
        star.setFill(Color.WHITE);
        g.getChildren().add(star);

        //adding the planets
        for(Planemo p : sys.getPlanets()){
            g.getChildren().addAll(getPlanet(p),getOrbit(p),getLabel(p));
        }

        StackPane pane = new StackPane();
        pane.setPrefSize(800,800);
        pane.getChildren().addAll(imageView, g);

        return new Scene(pane, 800, 800);
    }
    public Circle getOrbit(Planemo planet){ //returns an empty circle with the radius of the orbit
        Double radius = planet.getRadius();
        Circle orbit = new Circle(0,0,radius);
        orbit.setStroke(Color.WHITE);
        orbit.setFill(Color.TRANSPARENT);
        return orbit;
    }

    public Circle getPlanet(Planemo planet){ //returns a filled circle that is on the orbit
        Double size = planet.getSize();
        Double radius = planet.getRadius();
        //use the angle and radius to display it on the x y plane
        //x = r * cos(degrees) y = r * sin(degrees)
        double x = radius * cos(planet.getAngle());
        double y = radius * sin(planet.getAngle());

        Circle circle = new Circle(x,y,size);
        circle.setFill(Color.WHITE);
        return circle;
    }

    public Text getLabel(Planemo planet){
        Double radius = planet.getRadius();
        double x = radius * cos(planet.getAngle());
        double y = radius * sin(planet.getAngle()) - 15;
        Text text = new Text(planet.getName());
        text.setFont(Font.font("Century"));
        text.setFill(Color.WHITE);
        text.setX(x);
        text.setY(y);
        return text;
    }

    public ArrayList<Planemo> getPlanets(){
        return sys.getPlanets();
    }

    public String[] getNames(){
        ArrayList<String> names = new ArrayList<String>();
        for(Planemo p: sys.getPlanets()){
            names.add(p.getName());
        }
        return names.toArray(new String[names.size()]);
    }
}
