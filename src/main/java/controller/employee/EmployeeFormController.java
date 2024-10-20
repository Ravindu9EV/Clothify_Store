package controller.employee;

import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.*;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    public JFXTextField txtPassword;
    public JFXTextField txtContact;
    public TableColumn colContact;
    public TableColumn colPassword;
    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Employee> tblEmployees;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    EmployeeService service= ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {


       try {
           getTxtValues();
            if(service.addEmployee(getTxtValues())){
                loadEmployeeTable();
                new Alert(Alert.AlertType.INFORMATION,"Added...").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Smoething Wrong!").show();
            }
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Empty Fields!").show();
        }

    }

    private Employee getTxtValues() {
        return new Employee(txtID.getText(),txtName.getText(),txtContact.getText(),txtCompany.getText(),txtEmail.getText(),txtPassword.getText());

    }

    private void loadEmployeeTable() {
        ObservableList<Employee> employees= FXCollections.observableArrayList();

         service.getAll().forEach(employee -> {
             employees.add(employee);
         });
        tblEmployees.setItems(employees);
    }

    @FXML
    void btnDeleteEmployeeOnAction(ActionEvent event) {
        if(service.deleteEmployee(txtID.getText())){
            loadEmployeeTable();
            new Alert(Alert.AlertType.INFORMATION,"Employee Deleted...").show();
            clearTxtFields();
        }else{
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnSearchEmployeeOnAction(ActionEvent event) {
        if(service.searchEmployee(txtID.getText())!=null){
            new Alert(Alert.AlertType.INFORMATION,"Employee Found");
        }else{
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnUpdateEmployeeOnAction(ActionEvent event) {

        try{
            if(service.updateEmployee(getTxtValues())){
                loadEmployeeTable();
                new Alert(Alert.AlertType.INFORMATION,"Updated...").show();
                clearTxtFields();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong!").show();
            }
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Empty Fields!").show();
            clearTxtFields();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ------Mapping Table Columns with Object Attributes---------------
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        loadEmployeeTable();

        tblEmployees.getSelectionModel().selectedItemProperty().addListener((observableValue, employee, newEmployee) ->{
            try{
               addValuesToTxt(newEmployee);
            }catch (RuntimeException e){
                new Alert(Alert.AlertType.ERROR).show();
            }
        } );
    }

    private void addValuesToTxt(Employee employee) {

        txtID.setText(generateID());
        txtName.setText(employee.getName());
        txtEmail.setText(employee.getEmail());
        txtContact.setText(employee.getContact());
        txtCompany.setText(employee.getCompany());
        txtPassword.setText(employee.getPassword());
    }

    private void clearTxtFields(){
        txtID.clear();
        txtName.clear();
        txtContact.clear();
        txtCompany.clear();
        txtEmail.clear();
        txtPassword.clear();
    }


//-------------------Generate ID--------------------------

    public String generateID() {
        String code="";

        do{
            code=String.format("E%06d", new Random().nextInt(9999) + 1);
        }
        while (service.searchEmployee(code)==null);
        return code;
    }

    public void btnGenerateIDOnAction(ActionEvent actionEvent) {
        txtID.setText(generateID());
    }
}
