package controller.supplier;

import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
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
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
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

    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {

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
    }
}
