package controller.customer;

import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.*;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerFormController  implements Initializable {

    public TableView<Customer> tblCustomers;
    public JFXTextField txtContact;
    public TableColumn colContact;
    public ComboBox combCustomerID;


    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;





    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;


    CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);






    public String generateCustomerID() {
        int count=0;
        String code="";
        do{
            code=String.format("C%06d", count + 1);
        }while (customerService.searchCustomerByID(code)==null);

        return code;
    }


    public void btnGenerateIDOnAction(ActionEvent actionEvent) {
        txtID.setText(generateCustomerID());

    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
        if(customerService.deleteCustomer(txtID.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
            loadCustomerTable();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        if(customerService.updateCustomer(getTxtFieldsDetail())){

            new Alert(Alert.AlertType.INFORMATION,"Customer Details Updated!").show();
            loadCustomerTable();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        OrderService orderService=ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
//        orderService.search(txtID.getText(),tx)
    }

    public void btnSendReceiptToCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        if(customerService.addCustomer(getTxtFieldsDetail())){
            loadCustomerTable();
            new Alert(Alert.AlertType.INFORMATION,"Customer Added...").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTblCustomerCols();
        loadCustomerTable();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if(t1!=null){
                addValueToTxtFields(t1);
            }
        });
    }

    //---------map Customer Table Columns----------------
    public void setTblCustomerCols(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    //--------------------load Table--------------------------
    public void loadCustomerTable(){
        ObservableList<Customer> customers= FXCollections.observableArrayList();
        try {
            customerService.getAll().forEach(customer -> {
                customers.add(customer);
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tblCustomers.setItems(customers);
    }

    //------------------Add Selected Item values to Text Fields-----------------------
    public void addValueToTxtFields(Customer customer){
        txtID.setText(customer.getId());
        txtName.setText(customer.getName());
        txtEmail.setText(customer.getEmail());
        txtContact.setText(customer.getContact());
    }

    //-------------clear Text Fields----------------------------

    public void clearTxt(){
        txtID.clear();
        txtEmail.clear();
        txtName.clear();
        txtContact.clear();
    }

    public void btnClearTextOnAction(ActionEvent actionEvent) {
        clearTxt();
    }

    //--------------Get Text Fields Value---------------
    public Customer getTxtFieldsDetail(){
        return new Customer(
                txtID.getText().toString(),
                txtName.getText(),
                txtEmail.getText(),
                txtContact.getText()
        );
    }

        private void loadCustomerIDs(){
        ObservableList<String> ids=FXCollections.observableArrayList();
        try {
            for(Customer customer:customerService.getAll()){
                ids.add(customer.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        combCustomerID.setItems(ids);
    }
}
