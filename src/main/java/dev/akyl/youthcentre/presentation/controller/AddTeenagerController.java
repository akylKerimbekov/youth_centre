package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.Teenager;
import dev.akyl.youthcentre.service.TeenagerService;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTeenagerController {
    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAge;

    private ObservableList<Teenager> appMainObservableList;

    public void btnAddPersonClicked(ActionEvent actionEvent) {
        System.out.println("btnAddPersonClicked");

        String name = tfName.getText().trim();

        Teenager data = new Teenager();
        data.setFirstName(name);
        appMainObservableList.add(data);

        TeenagerService.getInstance().save(data);
        closeStage(actionEvent);
    }

    public void setAppMainObservableList(ObservableList<Teenager> tvObservableList) {
        this.appMainObservableList = tvObservableList;

    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void btnAddPersonCancel(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }
}
