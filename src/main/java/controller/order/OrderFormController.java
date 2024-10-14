package controller.order;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class OrderFormController {

    public TableColumn colOrderPaymentType;
    public ComboBox combPaymentType;
    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderProductDiscount;

    @FXML
    private TableColumn<?, ?> colOrderProductID;

    @FXML
    private TableColumn<?, ?> colOrderProductName;

    @FXML
    private TableColumn<?, ?> colOrderProductQuantity;

    @FXML
    private TableColumn<?, ?> colOrderProductTotalCost;

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
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private TableView<?> tblProducts;

    @FXML
    private JFXTextField txtProductID;

    @FXML
    private JFXTextField txtProductQuantity;

    @FXML
    private JFXTextField txtStockID;

    @FXML
    private JFXTextField txtStockUpdatedTime;

    @FXML
    void btnAddToOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveFromOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

}
