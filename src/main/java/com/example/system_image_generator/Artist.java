package com.example.system_image_generator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Artist {
    StarSystem sys;
    Planemo thalsiedeln;
    public Artist(){
        sys = new StarSystem("Arlioux"); //Making a default system used for testing purposes
        thalsiedeln = new Planemo("Thalsiedeln", 10, 100); //size should be in earth radii and radius is in AU
        sys.addPlanet(thalsiedeln); //It has one planet
    }

    public Scene draw() throws FileNotFoundException {
        GridPane grid = new GridPane();

        //all of this makes the background image ):
        FileInputStream inputstream = new FileInputStream("src/assets/LumiraBackground.png");
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(500);
        imageView.setFitWidth(500);

        //the ground we will add (planets and stuff)
        Group g = new Group();

        //adding the star
        Circle star = new Circle(0, 0, 30);
        star.setFill(Color.WHITE);
        g.getChildren().add(star);

        //adding the planets
        Circle planet1 = new Circle(5, 50, 10);
        g.getChildren().addAll(getPlanet(thalsiedeln),getOrbit(thalsiedeln));


        StackPane pane = new StackPane();
        pane.setPrefSize(500,500);
        pane.getChildren().addAll(imageView, g);

        return new Scene(pane, 500, 500);
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
        Circle circle = new Circle(0,radius,size);
        circle.setFill(Color.WHITE);
        return circle;
    }
}
