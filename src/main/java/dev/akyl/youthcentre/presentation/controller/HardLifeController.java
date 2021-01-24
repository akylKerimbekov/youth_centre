package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.HardLifeRef;
import dev.akyl.youthcentre.repository.entity.PsychoActiveRef;
import dev.akyl.youthcentre.service.HardLifeService;
import dev.akyl.youthcentre.service.PsychoActiveReferenceService;
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

public class HardLifeController implements Initializable {

    @FXML
    private TableView<HardLifeRef> tvHardLife;
    @FXML
    private TableColumn<HardLifeRef, String> hardLifeId;
    @FXML
    private TableColumn<HardLifeRef, String> hardLifeCode;
    @FXML
    private TableColumn<HardLifeRef, String> hardLifeDetail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvHardLife.setEditable(true);
        Callback<TableColumn<HardLifeRef, String>, TableCell<HardLifeRef, String>> cellFactory =
                (TableColumn<HardLifeRef, String> param) -> new EditingCell();

        hardLifeId.setCellValueFactory(new PropertyValueFactory<HardLifeRef, String>("id"));
        hardLifeCode.setCellValueFactory(new PropertyValueFactory<HardLifeRef, String>("code"));
        hardLifeCode.setCellFactory(cellFactory);
        hardLifeCode.setOnEditCommit(
                (TableColumn.CellEditEvent<HardLifeRef, String> t) -> {
                    ((HardLifeRef) t.getTableView()
                            .getItems()
                            .get(t.getTablePosition().getRow())).setCode(t.getNewValue());
                });
        hardLifeDetail.setCellValueFactory(new PropertyValueFactory<HardLifeRef, String>("detail"));
        hardLifeDetail.setCellFactory(cellFactory);
        hardLifeDetail.setOnEditCommit(
                (TableColumn.CellEditEvent<HardLifeRef, String> t) -> {
                    ((HardLifeRef) t.getTableView()
                            .getItems()
                            .get(t.getTablePosition().getRow())).setDetail(t.getNewValue());
                });

        tvHardLife.setItems(HardLifeService.getInstance().findAll());
    }

    public void addRow(ActionEvent actionEvent) {
        HardLifeRef hardLifeRef = new HardLifeRef();
        ObservableList<HardLifeRef> list = HardLifeService.getInstance().getHardLifeRefObservableList();
        list.add(hardLifeRef);
    }

    public void saveRow(ActionEvent actionEvent) {
        for (HardLifeRef hardLifeRef: HardLifeService.getInstance().getHardLifeRefObservableList()) {
            if (hardLifeRef.getId() == 0) {
                hardLifeRef.setCreated(LocalDateTime.now());
                hardLifeRef.setUpdated(LocalDateTime.now());
                HardLifeService.getInstance().save(hardLifeRef);
                System.out.println(hardLifeRef);
            } else  {
                hardLifeRef.setUpdated(LocalDateTime.now());
                HardLifeService.getInstance().update(hardLifeRef);
            }
        }
    }

    public void btnClose(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    class EditingCell extends TableCell<HardLifeRef, String> {

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
