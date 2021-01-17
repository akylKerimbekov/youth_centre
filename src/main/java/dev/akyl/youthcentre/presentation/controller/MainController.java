package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.Request;
import dev.akyl.youthcentre.repository.entity.Teenager;
import dev.akyl.youthcentre.service.RequestService;
import dev.akyl.youthcentre.service.TeenagerService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Teenager> teenager;
    @FXML
    private TableColumn<Teenager, String> teenFirstName;
    @FXML
    private TableColumn<Teenager, String> teenLastName;
    @FXML
    private TableColumn<Teenager, LocalDate> teenBirthday;
    @FXML
    private TableColumn<Teenager, String> teenSex;
    @FXML
    private TableColumn<Teenager, Integer> teenAge;

    @FXML
    TableView<Request> request;
    @FXML
    TableColumn<Request, Long> requestId;
    @FXML
    TableColumn<Request, String> requestNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teenFirstName.setCellValueFactory(new PropertyValueFactory<Teenager, String>("firstName"));
        teenLastName.setCellValueFactory(new PropertyValueFactory<Teenager, String>("lastName"));
        teenBirthday.setCellValueFactory(new PropertyValueFactory<Teenager, LocalDate>("birthday"));
        teenSex.setCellValueFactory(new PropertyValueFactory<Teenager, String>("sex"));

        teenager.setItems(TeenagerService.getInstance().findAll());
        teenager.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("teenager");
                request.setItems(RequestService.getInstance().findByTeenagerId(newSelection.getId()));
            }
        });

        requestId.setCellValueFactory(new PropertyValueFactory<Request, Long>("id"));
        requestNum.setCellValueFactory(new PropertyValueFactory<Request, String>("number"));
        request.setItems(RequestService.getInstance().findAll());
        request.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("request");
                teenager.getSelectionModel().clearSelection();
            }
        });
    }

    @FXML
    public void addTeenager(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add_teenager.fxml"));
        Parent parent = fxmlLoader.load();
        AddTeenagerController dialogController = fxmlLoader.<AddTeenagerController>getController();
        dialogController.setAppMainObservableList(TeenagerService.getInstance().getTeenagers());

        Scene scene = new Scene(parent, 500, 300);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void updateTeenager(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/update_teenager.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateTeenagerController dialogController = fxmlLoader.<UpdateTeenagerController>getController();

        Teenager temp = teenager.getSelectionModel().getSelectedItem();
        dialogController.setTeenager(temp);

        Scene scene = new Scene(parent, 500, 300);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void addRequest(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add_request.fxml"));
        Parent parent = fxmlLoader.load();
        AddRequestController dialogController = fxmlLoader.<AddRequestController>getController();
        dialogController.setAppRequestObservableList(RequestService.getInstance().getRequests());
        Teenager temp = teenager.getSelectionModel().getSelectedItem();
        dialogController.setTeenager(temp);

        Scene scene = new Scene(parent, 500, 300);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void updateRequest(ActionEvent actionEvent) {

    }
}
