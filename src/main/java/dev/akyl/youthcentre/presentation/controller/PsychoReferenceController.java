package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.PsychoActiveRef;
import dev.akyl.youthcentre.service.PsychoActiveReferenceService;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PsychoReferenceController implements Initializable {

    @FXML
    private TableView tvPsychoActiveRef;
    @FXML
    private TableColumn<PsychoActiveRef, String> psychoId;
    @FXML
    private TableColumn<PsychoActiveRef, String> psychoCode;
    @FXML
    private TableColumn<PsychoActiveRef, String> psychoDetail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvPsychoActiveRef.setEditable(true);
        Callback<TableColumn<PsychoActiveRef, String>, TableCell<PsychoActiveRef, String>> cellFactory =
                (TableColumn<PsychoActiveRef, String> param) -> new EditingCell();

        /*
        Callback<TableColumn<PsychoActiveRef, String>, TableCell<PsychoActiveRef, String>> cellFactory =
                new Callback<TableColumn<PsychoActiveRef, String>, TableCell<PsychoActiveRef, String>>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

         */
        psychoId.setCellValueFactory(new PropertyValueFactory<PsychoActiveRef, String>("id"));
//        psychoId.setCellFactory(cellFactory);
        psychoCode.setCellValueFactory(new PropertyValueFactory<PsychoActiveRef, String>("code"));
        psychoCode.setCellFactory(cellFactory);
        psychoCode.setOnEditCommit(
                (TableColumn.CellEditEvent<PsychoActiveRef, String> t) -> {
                    ((PsychoActiveRef) t.getTableView()
                            .getItems()
                            .get(t.getTablePosition().getRow())).setCode(t.getNewValue());
                });
        psychoDetail.setCellValueFactory(new PropertyValueFactory<PsychoActiveRef, String>("detail"));
        psychoDetail.setCellFactory(cellFactory);
        psychoDetail.setOnEditCommit(
                (TableColumn.CellEditEvent<PsychoActiveRef, String> t) -> {
                    ((PsychoActiveRef) t.getTableView()
                            .getItems()
                            .get(t.getTablePosition().getRow())).setDetail(t.getNewValue());
                });

        tvPsychoActiveRef.setItems(PsychoActiveReferenceService.getInstance().findAll());
    }

    public void btnClose(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void addRow(ActionEvent actionEvent) {
        PsychoActiveRef psychoActiveRef = new PsychoActiveRef();
//        tvPsychoActiveRef.getItems().add(psychoActiveRef);
        ObservableList<PsychoActiveRef> list = PsychoActiveReferenceService.getInstance().getPsychoActiveReferences();
        list.add(psychoActiveRef);
    }

    public void saveRow(ActionEvent actionEvent) {
        for (PsychoActiveRef psychoActive: PsychoActiveReferenceService.getInstance().getPsychoActiveReferences()) {
            if (psychoActive.getId() == 0) {
                psychoActive.setCreated(LocalDateTime.now());
                psychoActive.setUpdated(LocalDateTime.now());
                PsychoActiveReferenceService.getInstance().save(psychoActive);
                System.out.println(psychoActive);
            } else  {
                psychoActive.setUpdated(LocalDateTime.now());
                PsychoActiveReferenceService.getInstance().update(psychoActive);
            }
        }
    }

    class EditingCell extends TableCell<PsychoActiveRef, String> {

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
