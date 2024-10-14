package controller.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import util.DaoType;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private Label lblForgotPwd;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        Stage stage=new Stage();
        DaoType userType=LoginController.getInstance().checkLogin(txtEmail.getText(), txtPassword.getText());
        System.out.println(userType);
        if(userType==DaoType.ADMIN){
            try {
                System.out.println("Hi");
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/admin_dash_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else if(userType==DaoType.EMPLOYEE){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/employee_dash_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"User Not Found!").show();
        }
    }

}
