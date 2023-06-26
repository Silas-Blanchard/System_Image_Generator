package com.example.system_image_generator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application  {
    Stage window;
    Scene scenewa, scenetwa;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setTitle("Wa Burger");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(8);
        grid.setHgap(10);

        //name label
        Label nameLabel = new Label("Username");
        GridPane.setConstraints(nameLabel, 0, 0);


        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}