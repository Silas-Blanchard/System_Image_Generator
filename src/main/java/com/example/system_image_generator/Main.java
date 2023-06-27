package com.example.system_image_generator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application  {
    Stage window;
    Stage menu;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setTitle("System View");
        window.setWidth(800);
        window.setHeight(800);

        menu = new Stage();
        //menu.setTitle("Customize System");
        menu.setWidth(250);
        menu.setHeight(600);
        menu.setX(125);


        window.setOnCloseRequest(event -> {
            menu.close();
            //this little doohiky makes it so that both close when the map closes
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(8);
        grid.setHgap(10);

        Artist imago = new Artist();
        window.setScene(imago.draw());
        window.show();

        //setting up the menu with all its buttons and such

        FXMLLoader f = new FXMLLoader();
        final Parent fxmlRoot = (Parent)f.load(new FileInputStream(new File("src/main/resources/com/example/system_image_generator/hello-view.fxml")));
        menu.setScene(new Scene(fxmlRoot));

        Button addPlanet = new Button("Add Planet");
        addPlanet.setOnAction(e -> System.out.println("Wa"));

        VBox box = buttonSetup(imago.getPlanets());

        Scene menuScene = new Scene(box, 250, 600);
        //menu.setScene(menuScene);
        menu.show();
    }

    public VBox buttonSetup(ArrayList<Planemo> planets){ //requires menu so it can go first
        VBox planetButtons = new VBox();

        for(Planemo p: planets){ //makes a button corresponding to each planet
            Button b = new Button(p.getName());
            b.setOnAction(e -> System.out.println(p.getName()));
            planetButtons.getChildren().add(b);
        }
        return planetButtons;
    }

//    public MenuBar menuSetup(){
////        MenuBar mainMenu = new MenuBar();
////
////        final Menu menu4 = new Menu("Edit");
////        final Menu menu2 = new Menu("Settings");
////        MenuItem menu3 = new MenuItem("Help");
////
////        //setting up "file" I think fxml has an infinitely faster way to do this
////        final Menu menu1 = new Menu("File");
//    }

    public static void main(String[] args) {
        launch();
    }
}