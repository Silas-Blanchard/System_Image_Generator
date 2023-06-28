package com.example.system_image_generator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditController {
    @FXML
    private TextField editName;

    @FXML
    private TextField editSize;

    @FXML
    private TextField editDistance;

    @FXML
    private TextField editAngle;

    @FXML
    private TextField editMoons;

    @FXML
    private ButtonBar bar;

    @FXML
    private Button cancel;

    @FXML
    private Button submit;

    //these two allow this one to communicate with the other stages
    private MenuController mainMenu;

    String oldName;


    @FXML
    protected void handleActionEvent(ActionEvent e){

    }
    @FXML
    protected void cancel(ActionEvent e){
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void submit(ActionEvent e) throws FileNotFoundException {
        Planemo p = new Planemo(editName.getText(), Double.parseDouble(editSize.getText()), Double.parseDouble(editDistance.getText()));
        p.setAngle(Double.parseDouble(editAngle.getText()));
        p.setMoons(Integer.parseInt(editMoons.getText()),p);
        mainMenu.redraw(p,oldName);

        //finds and closes the right stage
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }

    public void displayPrompts(Planemo p, MenuController men){
        this.mainMenu = men;
        editName.setText(p.getName());
        editSize.setText(Double.toString(p.getSize()));
        editDistance.setText(Double.toString(p.getRadius()));
        editAngle.setText(Double.toString(Math.toDegrees(p.getAngle())));

        //a special snowflake that can be null if the planet had no moons
        editMoons.setText(Integer.toString(p.getMoons().size()));

        this.oldName  = editName.getText();
    }
}
