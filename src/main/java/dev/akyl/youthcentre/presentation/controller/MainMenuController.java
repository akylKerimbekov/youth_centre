package dev.akyl.youthcentre.presentation.controller;

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

        Scene scene = new Scene(parent, 800, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void openHardLife(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hardlife_reference.fxml"));
        Parent parent = fxmlLoader.load();
        HardLifeController dialogController = fxmlLoader.<HardLifeController>getController();

        Scene scene = new Scene(parent, 800, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void openDeliveryService(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/deliveryservice_reference.fxml"));
        Parent parent = fxmlLoader.load();
        DeliveryServiceController dialogController = fxmlLoader.<DeliveryServiceController>getController();

        Scene scene = new Scene(parent, 800, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void openSurvey(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/survey_ref.fxml"));
        Parent parent = fxmlLoader.load();
        SurveyRefController surveyRefController = fxmlLoader.<SurveyRefController>getController();

        Scene scene = new Scene(parent, 800, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
