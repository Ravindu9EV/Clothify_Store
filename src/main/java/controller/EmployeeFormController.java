package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeeFormController {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<?> tblEmployees;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateEmployeeOnAction(ActionEvent event) {

    }

}
