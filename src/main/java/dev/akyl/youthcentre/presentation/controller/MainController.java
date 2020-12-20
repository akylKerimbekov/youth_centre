package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.Request;
import dev.akyl.youthcentre.repository.entity.Teenager;
import dev.akyl.youthcentre.service.RequestService;
import dev.akyl.youthcentre.service.TeenagerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

        requestId.setCellValueFactory(new PropertyValueFactory<Request, Long>("id"));
        requestNum.setCellValueFactory(new PropertyValueFactory<Request, String>("number"));
        request.setItems(RequestService.getInstance().findAll());
    }
}
