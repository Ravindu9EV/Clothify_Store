package controller.admin;

import com.jfoenix.controls.JFXTextField;
import dto.Admin;
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
import service.custom.AdminService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminProfileEditControllerForm implements Initializable {

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableView<Admin> tblAdmin;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    AdminService service=ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
    @FXML
    void btnAddAdminOnAction(ActionEvent event) {
        try {


                service.addAdmin(getTxtValues());
                loadAdminTable();
                new Alert(Alert.AlertType.INFORMATION,"Added...").show();

        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,""+e).show();
        }
    }

    @FXML
    void btnDeleteAdminOnAction(ActionEvent event) {
        if(service.deleteAdmin(txtID.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Deleted...").show();
            loadAdminTable();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnSearchAdminOnAction(ActionEvent event) {
        String id=txtID.getText();
        System.out.println(id+"hapo");

                    try{


                        addValueToTxt(service.searchAdmin(id));

                    }catch (NullPointerException e){
                        new Alert(Alert.AlertType.ERROR, "Not Found!").show();
                        clearTxtFields();
                    }



    }

    @FXML
    void btnUpdateAdminOnAction(ActionEvent event) {
        try{
            if(service.updateAdmin(getTxtValues())){
                loadAdminTable();
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


    private Admin getAdmin(){
       return new Admin( txtID.getText(),txtName.getText(),txtContact.getText(),txtEmail.getText(),txtPassword.getText());
    }

    private void addValueToTxt(Admin admin){
        txtID.setText(admin.getId());
        txtName.setText(admin.getName());
        txtContact.setText(admin.getContact());
        txtEmail.setText(admin.getEmail());
        txtPassword.setText(admin.getPassword());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        loadAdminTable();

    }
    //-------------Load Admin Table-----------------------
    private void loadAdminTable(){
        ObservableList<Admin> admins= FXCollections.observableArrayList();
        service.getAdmin().forEach(admin -> {
            admins.add(admin);
        });

        tblAdmin.setItems(admins);
    }

    //--------Provide Admin Objec--------------
    public Admin getTxtValues(){
        return new Admin(txtID.getText(),txtName.getText(),txtContact.getText(),txtEmail.getText(),txtPassword.getText());
    }

    public void clearTxtFields(){
        txtID.clear();
        txtName.clear();
        txtContact.clear();
        txtEmail.clear();
        txtPassword.clear();
    }
}
