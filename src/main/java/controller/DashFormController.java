package controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.custom.impl.LoginServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.util.Objects;

public class DashFormController {

    private Stage stage=new Stage();

    @FXML
    void btnAdminDashboardOnAction(ActionEvent event) {
        try {
            LoginServiceImpl.getInstance().setUserType(ServiceType.ADMIN);
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login_form.fxml"))));
            //stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/employee_form.fxml"))));
            //stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/supplier_form.fxml"))));
            stage.show();
            System.out.println(LoginServiceImpl.getInstance().getUserType());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeDashboardOnAction(ActionEvent event) {
        try {

            LoginServiceImpl.getInstance().setUserType(ServiceType.EMPLOYEE);
             stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login_form.fxml"))));
            //stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/admin_profile_edit_form.fxml"))));
            //stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/product_form.fxml"))));
            //stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/order_form.fxml"))));

            stage.show();
            System.out.println(LoginServiceImpl.getInstance().getUserType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
