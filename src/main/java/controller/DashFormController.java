package controller;


import controller.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.custom.impl.LoginServiceImpl;
import util.DaoType;

import java.io.IOException;

public class DashFormController {

    private Stage stage=new Stage();

    @FXML
    void btnAdminDashboardOnAction(ActionEvent event) {
        try {
            LoginController.getInstance().setUserType(DaoType.ADMIN);
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login_form.fxml"))));
            stage.show();
            System.out.println(LoginServiceImpl.getInstance().getUserType());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeDashboardOnAction(ActionEvent event) {
        try {
            LoginController.getInstance().setUserType(DaoType.EMPLOYEE);
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
