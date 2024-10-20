package controller.supplier;

import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.SupplierService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

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
    private TableView<Supplier> tblSuppliers;

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

    SupplierService service= ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        try{
            service.addSupplier(getTxtValues());
            loadSupplierTable();
            new Alert(Alert.AlertType.INFORMATION,"Added!").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Oops!\n"+e);
        }
    }

    private void loadSupplierTable() {
        ObservableList<Supplier> suppliers= FXCollections.observableArrayList();

        service.getAll().forEach(supplier -> {
            suppliers.add(supplier);
        });
        tblSuppliers.setItems(suppliers);
    }
    private void setTxtValues(Supplier supplier){
        txtID.setText(supplier.getId());
        txtName.setText(supplier.getName());
        txtCompany.setText(supplier.getCompany());
        txtItemID.setText(supplier.getProductID());
        txtEmail.setText(supplier.getEmail());
    }

    private Supplier getTxtValues() {
        return new Supplier(txtID.getText(),txtName.getText(),txtCompany.getText(),txtItemID.getText(),txtEmail.getText());
    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        if(service.deleteSupplier(txtID.getText())){

            loadSupplierTable();
            new Alert(Alert.AlertType.INFORMATION,"Deleted").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Oops!\n").show();
        }
    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {
        try{
            Supplier supplier=service.searchSupplier(txtID.getText());
            setTxtValues(supplier);
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Oops!\n"+e).show();
            clearTxtValues();
        }
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {
        try {
            System.out.println(getTxtValues());

            service.updateSupplier(getTxtValues());
            loadSupplierTable();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Oops!\n"+e).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colItemID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        loadSupplierTable();

        tblSuppliers.getSelectionModel().selectedItemProperty().addListener((observableValue, supplier, t1) -> {
            try{
                setTxtValues(t1);
            }catch (RuntimeException e){
                new Alert(Alert.AlertType.ERROR,"Oops!\n"+e);
            }
        });
    }

    void clearTxtValues(){
        txtID.clear();
        txtEmail.clear();
        txtName.clear();
        txtCompany.clear();
        txtItemID.clear();
        txtID.setText(generateID());
    }

    public void btnProductFormOnAction(ActionEvent actionEvent) {
        Stage stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/product_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateID() {
//
        String code="";
        do{
            code=String.format("S%04d", new Random().nextInt(9999) + 1);
        }while (service.searchSupplier(code)!=null);
        return code;


    }

    public void btnGenerateIDOnAction(ActionEvent actionEvent) {
        txtID.setText(generateID());
    }
}
