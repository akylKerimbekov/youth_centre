package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.Teenager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateTeenagerController implements Initializable {

    @FXML
    private TextField tfUpdName;

    private Teenager teenager;

    public void setTeenager(Teenager teenager) {
        this.teenager = teenager;
    }

    public void setTfUpdName(String value) {
        this.tfUpdName.setText(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tfUpdName.setText(teenager.getFirstName());
    }

    public void btnUpdateTeenager(ActionEvent actionEvent) {
    }

    public void btnUpdateTeenagerCancel(ActionEvent actionEvent) {
    }
}
