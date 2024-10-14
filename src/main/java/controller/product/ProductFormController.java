package controller.product;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class ProductFormController {

    @FXML
    private TableColumn<?, ?> colProductCategory;

    @FXML
    private TableColumn<?, ?> colProductID;

    @FXML
    private TableColumn<?, ?> colProductImage;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colProductPrice;

    @FXML
    private TableColumn<?, ?> colProductQuantity;

    @FXML
    private TableColumn<?, ?> colProductSize;

    @FXML
    private ComboBox<?> combProductCategory;

    @FXML
    private ComboBox<?> combProductSize;

    @FXML
    private ImageView imgProductImage;

    @FXML
    private TableView<?> tblProducts;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtProductPrice;

    @FXML
    private JFXTextField txtProductQuantity;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
