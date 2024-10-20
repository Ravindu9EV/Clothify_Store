package controller.report;

import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {

    public Label lblID;
    @FXML
    private PieChart pieChart;


    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {

    }
    public String generateID() {


        return String.format("P%06d", new Random().nextInt(9999) + 1);


    }
    public void btnGenerateIDOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setReportID();
    }
    public void setReportID(){
        lblID.setText(generateID());
    }
}
