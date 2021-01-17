package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.Request;
import dev.akyl.youthcentre.repository.entity.Teenager;
import dev.akyl.youthcentre.service.RequestService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddRequestController {
    private ObservableList<Request> appRequestObservableList;
    private Teenager teenager;

    @FXML
    TextField tfNum;
    @FXML
    TextArea taAddiction;
    @FXML
    TextArea taConsultation;
    @FXML
    TextArea taDeviation;
    @FXML
    TextArea taSupport;

    public Teenager getTeenager() {
        return teenager;
    }

    public void setTeenager(Teenager teenager) {
        this.teenager = teenager;
    }

    public ObservableList<Request> getAppRequestObservableList() {
        return appRequestObservableList;
    }

    public void setAppRequestObservableList(ObservableList<Request> appRequestObservableList) {
        this.appRequestObservableList = appRequestObservableList;
    }

    public void btnAddRequest(ActionEvent actionEvent) {
        Request request = new Request();
        request.setTeenagerId(teenager.getId());
        request.setNumber(tfNum.getText());
        request.setAddiction(taAddiction.getText());
        request.setConsultation(taConsultation.getText());
        request.setDeviation(taDeviation.getText());
        request.setSupport(taSupport.getText());
        RequestService.getInstance().save(request);
        closeStage(actionEvent);
    }

    public void btnCancelRequest(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
