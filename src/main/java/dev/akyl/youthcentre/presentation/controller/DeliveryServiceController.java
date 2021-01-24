package dev.akyl.youthcentre.presentation.controller;

import dev.akyl.youthcentre.repository.entity.DeliveryServiceRef;
import dev.akyl.youthcentre.service.DeliveryServiceRefService;
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

public class DeliveryServiceController implements Initializable {
    @FXML
    private TableView<DeliveryServiceRef> tvDeliveryService;
    @FXML
    private TableColumn<DeliveryServiceRef, String> deliveryServiceId;
    @FXML
    private TableColumn<DeliveryServiceRef, String> deliveryServiceCode;
    @FXML
    private TableColumn<DeliveryServiceRef, String> deliveryServiceDetail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvDeliveryService.setEditable(true);
        Callback<TableColumn<DeliveryServiceRef, String>, TableCell<DeliveryServiceRef, String>> cellFactory =
                (TableColumn<DeliveryServiceRef, String> param) -> new EditingCell();

        deliveryServiceId.setCellValueFactory(new PropertyValueFactory<DeliveryServiceRef, String>("id"));
        deliveryServiceCode.setCellValueFactory(new PropertyValueFactory<DeliveryServiceRef, String>("code"));
        deliveryServiceCode.setCellFactory(cellFactory);
        deliveryServiceCode.setOnEditCommit(
                (TableColumn.CellEditEvent<DeliveryServiceRef, String> t) -> {
                    ((DeliveryServiceRef) t.getTableView()
                            .getItems()
                            .get(t.getTablePosition().getRow())).setCode(t.getNewValue());
                });
        deliveryServiceDetail.setCellValueFactory(new PropertyValueFactory<DeliveryServiceRef, String>("detail"));
        deliveryServiceDetail.setCellFactory(cellFactory);
        deliveryServiceDetail.setOnEditCommit(
                (TableColumn.CellEditEvent<DeliveryServiceRef, String> t) -> {
                    ((DeliveryServiceRef) t.getTableView()
                            .getItems()
                            .get(t.getTablePosition().getRow())).setDetail(t.getNewValue());
                });

        tvDeliveryService.setItems(DeliveryServiceRefService.getInstance().findAll());
    }

    public void addRow(ActionEvent actionEvent) {
        DeliveryServiceRef deliveryServiceRef = new DeliveryServiceRef();
        ObservableList<DeliveryServiceRef> list = DeliveryServiceRefService.getInstance().getDeliveryServiceRefObservableList();
        list.add(deliveryServiceRef);
    }

    public void saveRow(ActionEvent actionEvent) {
        for (DeliveryServiceRef deliveryServiceRef : DeliveryServiceRefService.getInstance().getDeliveryServiceRefObservableList()) {
            if (deliveryServiceRef.getId() == 0) {
                deliveryServiceRef.setCreated(LocalDateTime.now());
                deliveryServiceRef.setUpdated(LocalDateTime.now());
                DeliveryServiceRefService.getInstance().save(deliveryServiceRef);
                System.out.println(deliveryServiceRef);
            } else {
                deliveryServiceRef.setUpdated(LocalDateTime.now());
                DeliveryServiceRefService.getInstance().update(deliveryServiceRef);
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

    class EditingCell extends TableCell<DeliveryServiceRef, String> {

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
