package com.example.system_image_generator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Label planets;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void handleActionEvent(){

    }

    @FXML
    protected void handleAboutAction(){

    }

    @FXML
    private ListView<String> planetList;

    Artist imago = new Artist();

    String[] planetNames = imago.getNames();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        planetList.getItems().addAll(planetNames);
    }
}