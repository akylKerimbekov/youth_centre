package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.Teenager;
import dev.akyl.youthcentre.service.TeenagerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateTeenagerController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfMiddleName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfINN;
    @FXML
    private TextField tfSex;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfContact;
    @FXML
    private DatePicker dpBirthday;


    private Teenager teenager;

    public void setTeenager(Teenager teenager) {
        this.teenager = teenager;
        setTfName(teenager.getFirstName());
        setTfLastName(teenager.getLastName());
        setTfMiddleName(teenager.getMiddleName());
        setTfEmail(teenager.getEmail());
        setTfINN(teenager.getInn());
        setTfSex(teenager.getSex());
        setTfAddress(teenager.getAddress());
        setTfContact(teenager.getContact());
        setDpBirthday(teenager.getBirthday());
    }

    public void setDpBirthday(LocalDate dpBirthday) {
        this.dpBirthday.setValue(dpBirthday);
    }

    public void setTfName(String value) {
        this.tfName.setText(value);
    }

    public void setTfLastName(String value) {
        this.tfLastName.setText(value);
    }

    public void setTfMiddleName(String value) {
        this.tfMiddleName.setText(value);
    }

    public void setTfEmail(String value) {
        this.tfEmail.setText(value);
    }

    public void setTfINN(String value) {
        this.tfINN.setText(value);
    }

    public void setTfSex(String value) {
        this.tfSex.setText(value);
    }

    public void setTfAddress(String value) {
        this.tfAddress.setText(value);
    }

    public void setTfContact(String value) {
        this.tfContact.setText(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void btnUpdateTeenager(ActionEvent actionEvent) {
        teenager.setFirstName(tfName.getText());
        teenager.setLastName(tfLastName.getText());
        teenager.setMiddleName(tfMiddleName.getText());
        teenager.setEmail(tfEmail.getText());
        teenager.setAddress(tfAddress.getText());
        teenager.setContact(tfContact.getText());
        teenager.setInn(tfINN.getText());
        teenager.setSex(tfSex.getText());
        teenager.setBirthday(dpBirthday.getValue());
        TeenagerService.getInstance().update(teenager);
        closeStage(actionEvent);
    }

    public void btnUpdateTeenagerCancel(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
