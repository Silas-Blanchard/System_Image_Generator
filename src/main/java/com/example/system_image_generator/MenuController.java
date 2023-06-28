package com.example.system_image_generator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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

    Artist imago = Artist.getInstance();

    String[] planetNames = imago.getNames();

    @FXML public void handleMouseClick(MouseEvent args0) throws IOException {
        String planetName = planetList.getSelectionModel().getSelectedItem();
        System.out.println("clicked on " + planetName);
        //opening the editor for this planet by converting from string to planemo
        Planemo p = imago.searchPlanet(planetList.getSelectionModel().getSelectedItem());
        if(!Objects.isNull(p)){ //if this planet exists
            openEditor(p);
        }
        //updating planet list
        //planetList.getItems().clear();
        planetList.getItems().clear();
        planetNames = imago.getNames();
        planetList.getItems().addAll(planetNames);
    }
    @Override
    public void initialize(URL urls, ResourceBundle resourceBundle) {
        planetList.getItems().clear();
        planetList.getItems().addAll(planetNames);
    }
    public void openEditor(Planemo p) throws IOException { //opens the editing window for a particular planet
        Stage editor = new Stage();
        FXMLLoader f = new FXMLLoader(getClass().getResource("editor.fxml"));
        Parent root = f.load();
        //passing info to the other controller
        EditController cont = f.getController();
        cont.displayPrompts(p,this);

        //final Parent fxmlRoot = (Parent)f.load(new FileInputStream(new File("src/main/resources/com/example/system_image_generator/editor.fxml")));

        editor.setScene(new Scene(root));
        editor.show();
    }

    public void redraw(Planemo p, String oldName) throws FileNotFoundException { //this will take in a new planemo and the old id to replace it
        imago.updatePlanet(oldName, p);
        planetNames = imago.getNames();
        planetList.getItems().clear();
        planetList.getItems().addAll(planetNames);
        imago.updateWindow();
    }
}