package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.SurveyRef;
import dev.akyl.youthcentre.service.SurveyRefService;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class SurveyRefController implements Initializable {

    @FXML
    private TableView<SurveyRef> tvSurvey;
    @FXML
    private TableColumn<SurveyRef, String> surveyId;
    @FXML
    private TableColumn<SurveyRef, String> surveyParentId;
    @FXML
    private TableColumn<SurveyRef, String> surveyCode;
    @FXML
    private TableColumn<SurveyRef, String> surveyDetail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvSurvey.setEditable(true);
        Callback<TableColumn<SurveyRef, String>, TableCell<SurveyRef, String>> cellFactory =
                (TableColumn<SurveyRef, String> param) -> new EditingCell();

        surveyId.setCellValueFactory(new PropertyValueFactory<SurveyRef, String>("id"));

        surveyParentId.setCellValueFactory(new PropertyValueFactory<SurveyRef, String>("parentId"));

        surveyCode.setCellValueFactory(new PropertyValueFactory<SurveyRef, String>("code"));
        surveyCode.setCellFactory(cellFactory);
        surveyCode.setOnEditCommit(
                (TableColumn.CellEditEvent<SurveyRef, String> t) -> {
                    ((SurveyRef) t.getTableView()
                            .getItems()
                            .get(t.getTablePosition().getRow())).setCode(t.getNewValue());
                });

        surveyDetail.setCellValueFactory(new PropertyValueFactory<SurveyRef, String>("detail"));
        surveyDetail.setCellFactory(cellFactory);
        surveyDetail.setOnEditCommit(
                (TableColumn.CellEditEvent<SurveyRef, String> t) -> {
                    ((SurveyRef) t.getTableView()
                            .getItems()
                            .get(t.getTablePosition().getRow())).setDetail(t.getNewValue());
                });

        tvSurvey.setItems(SurveyRefService.getInstance().findAll());
    }

    public void addRow(ActionEvent actionEvent) {
        SurveyRef surveyRef = new SurveyRef();
        ObservableList<SurveyRef> list = SurveyRefService.getInstance().getSurveyRefs();
        list.add(surveyRef);
    }

    public void saveRow(ActionEvent actionEvent) {
        for (SurveyRef surveyRef : SurveyRefService.getInstance().getSurveyRefs()) {
            if (surveyRef.getId() == 0) {
                surveyRef.setCreated(LocalDateTime.now());
                surveyRef.setUpdated(LocalDateTime.now());
                SurveyRefService.getInstance().save(surveyRef);
                System.out.println(surveyRef);
            } else {
                surveyRef.setUpdated(LocalDateTime.now());
                SurveyRefService.getInstance().update(surveyRef);
            }
        }
    }

    public void btnClose(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    class EditingCell extends TableCell<SurveyRef, String> {

        private TextField textField;

        private EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnAction((e) -> commitEdit(textField.getText()));
            textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    System.out.println("Commiting " + textField.getText());
                    commitEdit(textField.getText());
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }
}
