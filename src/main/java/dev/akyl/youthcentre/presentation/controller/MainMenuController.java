package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.Teenager;
import dev.akyl.youthcentre.service.RequestService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    public void generate(ActionEvent actionEvent) {

    }

    public void openPsychoActive(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/psycho_reference.fxml"));
        Parent parent = fxmlLoader.load();
        PsychoReferenceController dialogController = fxmlLoader.<PsychoReferenceController>getController();
        //dialogController.setAppRequestObservableList(RequestService.getInstance().getRequests());

        Scene scene = new Scene(parent, 800, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
