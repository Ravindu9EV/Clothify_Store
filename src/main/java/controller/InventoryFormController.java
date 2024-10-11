package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InventoryFormController {

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colProductQuantity;

    @FXML
    private TableColumn<?, ?> colStockID;

    @FXML
    private TableColumn<?, ?> colStockLastUpdatedDate;

    @FXML
    private TableColumn<?, ?> colStockLastUpdatedTime;

    @FXML
    private DatePicker dateStockUpdatedDate;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<?> tblStocks;

    @FXML
    private JFXTextField txtProductID;

    @FXML
    private JFXTextField txtProductQuantity;

    @FXML
    private JFXTextField txtStockID;

    @FXML
    private JFXTextField txtStockUpdatedTime;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
