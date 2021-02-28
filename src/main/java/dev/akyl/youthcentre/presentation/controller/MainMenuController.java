package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.App;
import dev.akyl.youthcentre.report.GenderReportService;
import dev.akyl.youthcentre.report.TeenagerReportService;
import dev.akyl.youthcentre.repository.HibernateUtil;
import dev.akyl.youthcentre.utils.Toast;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.File;
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

    public void backup(ActionEvent actionEvent) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query =  session.createSQLQuery("BACKUP TO 'backup.zip'");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void reportGender(ActionEvent actionEvent) throws IOException {
        File selectedFile = getFile();
        if (selectedFile != null) {
            GenderReportService.getReport(selectedFile);
            toast();
        }
    }

    private static File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel 97", "*.xlx")
                ,new FileChooser.ExtensionFilter("Excel 2007", "*.xlsx")
        );
        File selectedFile = fileChooser.showSaveDialog(App.getPrimaryStage());

        if (selectedFile != null)
        {
            if(!selectedFile.getName().contains(".")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".xlsx");
            }
        }

        return selectedFile;
    }

    public void reporTeenagers(ActionEvent actionEvent) {
        File selectedFile = getFile();
        if (selectedFile != null) {
            TeenagerReportService.getReport(selectedFile);
            toast();
        }

    }

    private static void toast(){
        String toastMsg = "Отчет выгружен...";
        int toastMsgTime = 500; //3.5 seconds
        int fadeInTime = 500; //0.5 seconds
        int fadeOutTime= 500; //0.5 seconds
        Toast.makeText(App.getPrimaryStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
    }
}
