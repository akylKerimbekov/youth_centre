package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.Request;
import dev.akyl.youthcentre.repository.entity.Teenager;
import dev.akyl.youthcentre.service.RequestService;
import dev.akyl.youthcentre.service.TeenagerService;
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
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Teenager> teenager;
    @FXML
    private TableColumn<Teenager, String> teenFirstName;
    @FXML
    private TableColumn<Teenager, String> teenLastName;

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

        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
