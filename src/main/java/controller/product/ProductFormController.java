package controller.product;

import com.jfoenix.controls.JFXTextField;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import service.ServiceFactory;
import service.custom.ProductService;
import util.ServiceType;

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

    private ProductService service;

    //--------------------Add Product -----------------
    @FXML
    void btnAddOnAction(ActionEvent event) {
        ProductService productService= ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        Product product=new Product(txtProductId.getText(),txtProductName.getText(),combProductSize.getValue().toString(),Double.parseDouble(txtProductPrice.getText()),Integer.parseInt(txtProductQuantity.getText()),combProductCategory.getValue().toString(),imgProductImage.getImage().getUrl());
        if(productService.addProduct(product)){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Added!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Somthing Wrong!").show();
        }
    }

    //--------------------Remove Product -----------------
    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    //--------------------Search Product -----------------
    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    //--------------------Update Product -----------------
    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
