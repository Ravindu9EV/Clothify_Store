package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<?> tblSuppliers;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtItemID;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {

    }

}
