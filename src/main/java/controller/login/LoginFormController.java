package controller.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import service.custom.impl.LoginServiceImpl;
import util.DaoType;
import util.ServiceType;

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
        ServiceType userType= LoginServiceImpl.getInstance().checkUserLogin(txtEmail.getText(),txtPassword.getText());


        System.out.println(userType);
        if(userType==ServiceType.ADMIN){
            try {
                System.out.println("Hi");
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/admin_dash_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else if(userType==ServiceType.EMPLOYEE){
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


    public void lblForgotPwdOnClick(MouseEvent mouseEvent) {

        System.out.println(txtEmail.getText().equals(""));
         if (txtEmail.getText().equals("") || txtPassword.getText().equals("")) {

            new Alert(Alert.AlertType.ERROR,"Make Sure to Enter Your Email and Password!").show();
        }else if(LoginServiceImpl.getInstance().findByEmail(txtEmail.getText())==null){
            new Alert(Alert.AlertType.ERROR,"User doesn't exist").show();
         }else if (LoginServiceImpl.getInstance().getUserType() != null) {
             Stage stage=new Stage();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/otp_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else{
            new Alert(Alert.AlertType.ERROR,"User doesn't exists,Make sure to check your Email and Password;").show();
        }


    }
}
