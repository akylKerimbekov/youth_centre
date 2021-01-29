package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.*;
import dev.akyl.youthcentre.service.DeliveryServiceRefService;
import dev.akyl.youthcentre.service.HardLifeService;
import dev.akyl.youthcentre.service.PsychoActiveReferenceService;
import dev.akyl.youthcentre.service.RequestService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateRequestController implements Initializable {
    private ObservableList<Request> appRequestObservableList;
    private Teenager teenager;
    private Request request;

    @FXML
    public ComboBox<DeliveryServiceRef> cbDeliveryService;
    @FXML
    public ComboBox<PsychoActiveRef> cbPsychoActive;
    @FXML
    public ComboBox<HardLifeRef> cbHardLife;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbPsychoActive.setItems(PsychoActiveReferenceService.getInstance().findAll());
        cbPsychoActive.setConverter(new StringConverter<PsychoActiveRef>() {
            @Override
            public String toString(PsychoActiveRef psychoActiveRef) {
                if (psychoActiveRef != null) {
                    return psychoActiveRef.getCode();
                }
                return "";
            }

            @Override
            public PsychoActiveRef fromString(String s) {
                return cbPsychoActive.getItems()
                        .stream()
                        .filter(ap -> ap.getCode().equals(s))
                        .findFirst()
                        .orElse(null);
            }
        });
        cbDeliveryService.setItems(DeliveryServiceRefService.getInstance().findAll());
        cbDeliveryService.setConverter(new StringConverter<DeliveryServiceRef>() {
            @Override
            public String toString(DeliveryServiceRef deliveryServiceRef) {
                if (deliveryServiceRef != null) {
                    return deliveryServiceRef.getCode();
                }
                return "";
            }

            @Override
            public DeliveryServiceRef fromString(String s) {
                return cbDeliveryService.getItems()
                        .stream()
                        .filter(ap -> ap.getCode().equals(s))
                        .findFirst()
                        .orElse(null);
            }
        });
        cbHardLife.setItems(HardLifeService.getInstance().findAll());
        cbHardLife.setConverter(new StringConverter<HardLifeRef>() {
            @Override
            public String toString(HardLifeRef hardLifeRef) {
                if (hardLifeRef != null) {
                    return hardLifeRef.getCode();
                }
                return "";
            }

            @Override
            public HardLifeRef fromString(String s) {
                return cbHardLife.getItems()
                        .stream()
                        .filter(ap -> ap.getCode().equals(s))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    public void setRequest(Request request) {
        this.request = request;
        tfNum.setText(request.getNumber());
        taAddiction.setText(request.getAddiction());
        taConsultation.setText(request.getConsultation());
        taDeviation.setText(request.getDeviation());
        taSupport.setText(request.getSupport());
        cbDeliveryService.getSelectionModel().select(request.getDeliveryServiceRef());
        cbHardLife.getSelectionModel().select(request.getHardLifeRef());
        cbPsychoActive.getSelectionModel().select(request.getPsychoActiveRef());
    }

    public void setAppRequestObservableList(ObservableList<Request> appRequestObservableList) {
        this.appRequestObservableList = appRequestObservableList;
    }

    public void btnUpdateRequest(ActionEvent actionEvent) {
        request.setNumber(tfNum.getText());
        request.setAddiction(taAddiction.getText());
        request.setConsultation(taConsultation.getText());
        request.setDeviation(taDeviation.getText());
        request.setSupport(taSupport.getText());
        request.setDeliveryServiceRef(cbDeliveryService.getSelectionModel().getSelectedItem());
        request.setHardLifeRef(cbHardLife.getSelectionModel().getSelectedItem());
        request.setPsychoActiveRef(cbPsychoActive.getSelectionModel().getSelectedItem());
        RequestService.getInstance().update(request);
        closeStage(actionEvent);
    }

    public void btnCancelRequest(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
