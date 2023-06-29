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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public final class Artist { //this is a singleton that literally everyone needs to be able to talk to
    private final static Artist imago = new Artist();
    private StarSystem sys;

    public static Artist getInstance() {
        return imago;
    }

    private Stage window;
    Planemo thalsiedeln;
    public Artist(){
        sys = new StarSystem("Arlioux"); //Making a default system used for testing purposes
        thalsiedeln = new Planemo("Thalsiedeln", 10.0, 100.0); //size should be in earth radii and radius is in AU
        thalsiedeln.setAngle(180);
        sys.addPlanet(thalsiedeln); //It has one planet

        //Another planet!
        Planemo belt = new Planemo("Belt", 10.0, 200.0); //size should be in earth radii and radius is in AU
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

        //adding the planets and moons
        for(Planemo p : sys.getPlanets()){
            g.getChildren().addAll(getPlanet(p),getOrbit(p),getLabel(p));
            for(Planemo m: p.getMoons()){
                g.getChildren().add(getMoon(m,p));
            }
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

    public Text getLabel(Planemo planet){ //Generates labels for the planets
        Double radius = planet.getRadius();
        double x = radius * cos(planet.getAngle()); //is in radians. Just where I think it should go
        double y = radius * sin(planet.getAngle());
        Text text = new Text(planet.getName());
        text.setFont(Font.font("Century", 15));
        text.setFill(Color.WHITE);
        final double width = text.getLayoutBounds().getWidth(); //finding width of text so we can center it
        x = x - width / 2;
        y = y - planet.getSize() - 15;
        text.setX(x);
        text.setY(y);
        return text;
    }

    public Circle getMoon (Planemo moon, Planemo planet){
        Double size = moon.getSize();
        Double radius = moon.getRadius();
        //use the angle and radius to display it on the x y plane
        //x = r * cos(degrees) y = r * sin(degrees)
        double originx = planet.getRadius() * cos(planet.getAngle());
        double originy = planet.getRadius() * sin(planet.getAngle());

        double moonx = radius * cos(moon.getAngle());
        double moony = radius * sin(moon.getAngle());

        double x = originx + moonx;
        double y = originy + moony;

        Circle circle = new Circle(x,y,size);
        circle.setFill(Color.WHITE);
        return circle;
    }

    public ArrayList<Planemo> getPlanets(){ //returns an arraylist of planets
        return sys.getPlanets();
    }

    public String[] getNames(){ //returns an array of planet names
        ArrayList<String> names = new ArrayList<String>();
        for(Planemo p: sys.getPlanets()){
            names.add(p.getName());
        }
        return names.toArray(new String[names.size()]);
    }

    public Planemo searchPlanet(String name){ //searches a planet based on its name
        for(Planemo p: sys.getPlanets()){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public void updatePlanet(String name, Planemo p){ //replaces one planet with another
        for(int i = 0; i < sys.getPlanets().size(); i++){
            if(sys.getPlanets().get(i).getName().equals(name)){
                sys.editPlanet(i,p);
            }
        }
    }
    public void setWindow(Stage window){
        this.window = window;
    }

    public void updateWindow() throws FileNotFoundException { //gets the window to update with a new product
        window.setScene(this.draw());
        window.close();
        window.show();
    }

}
