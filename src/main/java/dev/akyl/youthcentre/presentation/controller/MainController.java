package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.*;
import dev.akyl.youthcentre.service.RequestService;
import dev.akyl.youthcentre.service.SurveyRefService;
import dev.akyl.youthcentre.service.SurveyResultService;
import dev.akyl.youthcentre.service.TeenagerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
/*
    @FXML
    private TreeTableView<SurveyResultItem> survey;
    @FXML
    TreeTableColumn<SurveyResultItem, String> surveyChapter;
    @FXML
    TreeTableColumn<SurveyResultItem, String> surveyQuestion;
    @FXML
    TreeTableColumn<SurveyResultItem, String> surveyAnswer;
*/
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
    TableColumn<Request, String> requestNum;
    @FXML
    public TableColumn<Request, HardLifeRef> requestHardLife;
    @FXML
    public TableColumn<Request, PsychoActiveRef> requestPsychoActive;
    @FXML
    public TableColumn<Request, DeliveryServiceRef> requestDeliveryService;
    @FXML
    public TableColumn<Request, String> requestAddiction;
    @FXML
    public TableColumn<Request, String> requestDeviation;
    @FXML
    public TableColumn<Request, String> requestConsult;
    @FXML
    public TableColumn<Request, String> requestSupport;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*
        surveyChapter.setCellValueFactory(new TreeItemPropertyValueFactory<SurveyResultItem, String>("chapter"));
        surveyQuestion.setCellValueFactory(new TreeItemPropertyValueFactory<SurveyResultItem, String>("question"));
        surveyAnswer.setCellValueFactory(new TreeItemPropertyValueFactory<SurveyResultItem, String>("answer"));
*/
        teenFirstName.setCellValueFactory(new PropertyValueFactory<Teenager, String>("firstName"));
        teenLastName.setCellValueFactory(new PropertyValueFactory<Teenager, String>("lastName"));
        teenBirthday.setCellValueFactory(new PropertyValueFactory<Teenager, LocalDate>("birthday"));
        teenSex.setCellValueFactory(new PropertyValueFactory<Teenager, String>("sex"));

        teenager.setItems(TeenagerService.getInstance().findAll());
        teenager.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("teenager");
                request.setItems(RequestService.getInstance().findByTeenagerId(newSelection.getId()));
                request.refresh();
            }
        });
        /*
        teenager.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fullFillSurveyResultTree(newSelection.getId());
            }
        });

         */

        requestNum.setCellValueFactory(new PropertyValueFactory<Request, String>("number"));
        requestAddiction.setCellValueFactory(new PropertyValueFactory<Request, String>("addiction"));
        requestConsult.setCellValueFactory(new PropertyValueFactory<Request, String>("consultation"));
        requestDeviation.setCellValueFactory(new PropertyValueFactory<Request, String>("deviation"));
        requestSupport.setCellValueFactory(new PropertyValueFactory<Request, String>("support"));
        requestHardLife.setCellValueFactory(new PropertyValueFactory<Request, HardLifeRef>("hardLifeRef"));
        requestHardLife.setCellFactory(new Callback<TableColumn<Request, HardLifeRef>, TableCell<Request, HardLifeRef>>() {
            @Override
            public TableCell<Request, HardLifeRef> call(TableColumn<Request, HardLifeRef> param) {
                TableCell<Request, HardLifeRef> cell = new TableCell<Request, HardLifeRef>() {
                    @Override
                    protected void updateItem(HardLifeRef item, boolean empty) {
                        if (item != null) {
                            Label cityLabel = new Label(item.getCode());
                            setGraphic(cityLabel);
                        }
                    }
                };
                return cell;
            }
        });
        requestPsychoActive.setCellValueFactory(new PropertyValueFactory<Request, PsychoActiveRef>("psychoActiveRef"));
        requestPsychoActive.setCellFactory(new Callback<TableColumn<Request, PsychoActiveRef>, TableCell<Request, PsychoActiveRef>>() {
            @Override
            public TableCell<Request, PsychoActiveRef> call(TableColumn<Request, PsychoActiveRef> requestPsychoActiveRefTableColumn) {
                TableCell<Request, PsychoActiveRef> cell = new TableCell<Request, PsychoActiveRef>() {
                    @Override
                    protected void updateItem(PsychoActiveRef psychoActiveRef, boolean b) {
                        if (psychoActiveRef != null) {
                            Label label = new Label(psychoActiveRef.getCode());
                            setGraphic(label);
                        }
                    }
                };
                return cell;
            }
        });
        requestDeliveryService.setCellValueFactory(new PropertyValueFactory<Request, DeliveryServiceRef>("deliveryServiceRef"));
        requestDeliveryService.setCellFactory(new Callback<TableColumn<Request, DeliveryServiceRef>, TableCell<Request, DeliveryServiceRef>>() {
            @Override
            public TableCell<Request, DeliveryServiceRef> call(TableColumn<Request, DeliveryServiceRef> requestDeliveryServiceRefTableColumn) {
                TableCell<Request, DeliveryServiceRef> cell = new TableCell<Request, DeliveryServiceRef>() {
                    @Override
                    protected void updateItem(DeliveryServiceRef deliveryServiceRef, boolean b) {
                        if (deliveryServiceRef != null) {
                            Label label = new Label(deliveryServiceRef.getCode());
                            setGraphic(label);
                        }
                    }
                };
                return cell;
            }
        });

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

        Scene scene = new Scene(parent, 500, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void updateRequest(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/update_request.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateRequestController dialogController = fxmlLoader.<UpdateRequestController>getController();
        dialogController.setAppRequestObservableList(RequestService.getInstance().getRequests());
        dialogController.setRequest(request.getSelectionModel().getSelectedItem());

        Scene scene = new Scene(parent, 500, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void addSurvey(ActionEvent actionEvent) {

    }

    public void showSurvey(ActionEvent actionEvent) {

    }
/*
    private void fullFillSurveyResultTree(final long teenager) {
        //TreeTableColumn<SurveyResultItem, String> chapterColumn = new TreeTableColumn<>("Chapter");
        //TreeTableColumn<SurveyResultItem, String> questionColumn = new TreeTableColumn<>("Question");
        //TreeTableColumn<SurveyResultItem, String> answerColumn = new TreeTableColumn<>("Answer");

        //chapterColumn.setCellValueFactory(new TreeItemPropertyValueFactory<SurveyResultItem, String>("chapter"));
        //questionColumn.setCellValueFactory(new TreeItemPropertyValueFactory<SurveyResultItem, String>("question"));
        //answerColumn.setCellValueFactory(new TreeItemPropertyValueFactory<SurveyResultItem, String>("answer"));

        //survey.getColumns().addAll(surveyChapter, surveyQuestion, surveyAnswer);

        TreeItem<SurveyResultItem> root = new TreeItem<>(new SurveyResultItem());

        List<SurveyRef> surveyRefParentList = SurveyRefService.getInstance().findAllParents();
        System.out.println(surveyRefParentList);
        for (SurveyRef chapter : surveyRefParentList) {
            TreeItem<SurveyResultItem> chapterTreeItem = new TreeItem<>(new SurveyResultItem(chapter));

            for (SurveyRef child : SurveyRefService.getInstance().findChildren(chapter.getId())){
                SurveyResult answer = SurveyResultService.getInstance().findBySurveyRefId(child, teenager);
                TreeItem<SurveyResultItem> answerTreeItem = new TreeItem<>(new SurveyResultItem(child, answer));
                chapterTreeItem.getChildren().add(answerTreeItem);
            }

            root.getChildren().add(chapterTreeItem);

        }
        survey.setRoot(root);
        survey.setShowRoot(false);
        expandTreeView(survey.getRoot());
    }

    private void expandTreeView(TreeItem<?> item){
        if(item != null && !item.isLeaf()){
            item.setExpanded(true);
            for(TreeItem<?> child:item.getChildren()){
                expandTreeView(child);
            }
        }
    }

    private void clearSurvey() {
        if (survey != null && survey.getRoot() != null && survey.getRoot().getChildren() != null) {
            survey.getRoot().getChildren().clear();
        }
    }

    public void addSurveyResult(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add_survey_ref.fxml"));
        Parent parent = fxmlLoader.load();
        AddSurveyRefController dialogController = fxmlLoader.<AddSurveyRefController>getController();

        Scene scene = new Scene(parent, 500, 500);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

 */
}