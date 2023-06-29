package com.example.system_image_generator;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditController {
    @FXML
    private TextField editName;

    @FXML
    private Slider editSize;

    @FXML
    private Slider editDistance;

    @FXML
    private Slider editAngle;

    @FXML
    private Slider editMoons;

    @FXML
    private ButtonBar bar;

    @FXML
    private Button cancel;

    @FXML
    private Button submit;

    //okay now for a bunch of annoying garbage that adds labels to the sliders
    @FXML
    private Label sizeLabel;

    @FXML
    private Label moonsLabel;

    @FXML
    private Label angleLabel;

    @FXML
    private Label distanceLabel;

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
        Planemo p = new Planemo(editName.getText(), (Double) editSize.getValue(), (Double) editDistance.getValue());
        p.setAngle((Double) editAngle.getValue());
        p.setMoons((int) editMoons.getValue(),p);
        mainMenu.redraw(p,oldName);

        //finds and closes the right stage
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }

    public void displayPrompts(Planemo p, MenuController men){
        this.mainMenu = men;
        editName.setText(p.getName());
        editSize.setValue(p.getSize());
        editDistance.setValue(p.getRadius());
        editAngle.setValue(Math.toDegrees(p.getAngle()));
        editMoons.setValue(p.getMoons().size());

        this.oldName  = editName.getText();

        sizeLabel.textProperty().bind(Bindings.format("%.2f", editSize.valueProperty()));
        distanceLabel.textProperty().bind(Bindings.format("%.2f", editDistance.valueProperty()));
        angleLabel.textProperty().bind(Bindings.format("%.2f", editAngle.valueProperty()));
        moonsLabel.textProperty().bind(Bindings.format("%.0f", editMoons.valueProperty()));
    }
}
