package controller.order;

import com.jfoenix.controls.JFXTextField;
import dto.Cart;
import dto.Customer;
import dto.Order;
import dto.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ProductServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFormController {

    public TableColumn colOrderPaymentType;
    public ComboBox combPaymentType;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerContact;
    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerEmail;
    public Label lblUserID;
    public Label lblOrderID;
    public JFXTextField txtProductDiscount;
    public JFXTextField txtProductPrice;
    public ComboBox combProductID;
    public JFXTextField txtProductStock;
    public TableColumn colOrderProductPrice;
    public TableView<Customer> tblCustomers;
    public TableColumn colCustomerName;
    public TableColumn colCustomerEmail;
    public TableColumn colCustomerContact;

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
    private TableView<Cart> tblOrder;

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

    private ObservableList<Cart> cart= FXCollections.observableArrayList();
    @FXML
    void btnAddToOrderOnAction(ActionEvent event) {

        String orderID=lblOrderID.getText();
        String productID= combProductID.getValue().toString();
        Integer productQuantity=Integer.parseInt(txtProductQuantity.getText());
        Double productPrice=Double.parseDouble(txtProductPrice.getText());
        Double productDiscount=Double.parseDouble(txtProductDiscount.getText());
        Double totalPrice=(productPrice-productDiscount)*productQuantity;

        //------------Cart table---------

        colOrderProductID.setCellValueFactory(new PropertyValueFactory<>(productID));
        colOrderProductPrice.setCellValueFactory(new PropertyValueFactory<>(productPrice.toString()));
        colOrderProductQuantity.setCellValueFactory(new PropertyValueFactory<>(productQuantity.toString()));
        colOrderProductDiscount.setCellValueFactory(new PropertyValueFactory<>(productDiscount.toString()));
        colOrderProductTotalCost.setCellValueFactory(new PropertyValueFactory<>(totalPrice.toString()));

        //-----------Customer Table------------

        colProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("contact"));


        if(productQuantity<=Integer.parseInt(txtProductStock.getText())){

            cart.add(new Cart(productID,productPrice,productQuantity,productDiscount,totalPrice));
            tblOrder.setItems(cart);
            calcTotal();
        }



    }

    private void loadProductIDs(){
        ObservableList<String> productIDs= FXCollections.observableArrayList();
                ProductServiceImpl.getInstance().getAllIProducts().forEach(product -> {
                    productIDs.add(product.getId());
                }
        );combProductID.setItems(productIDs);
    }

    private void calcTotal() {
        Double total=0.0;
        for(Cart cartT:cart){
            total+=cartT.getProductTotalPrice();
        }
        lblTotal.setText(total.toString());
    }

    @FXML
    void btnRemoveFromOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        List<OrderDetail> orderDetails=new ArrayList<>();
        orderDetails.add(new OrderDetail(lblOrderID.getText(),txtProductID.getText(),Integer.parseInt(txtProductQuantity.getText()),Double.parseDouble(txtProductDiscount.getText())));

        Order order=new Order(txtStockID.getText(),lblUserID.getText(),txtCustomerID.getText(), LocalDate.parse(lblDate.getText()),combPaymentType.getValue().toString(),orderDetails);
        try {
            if(new OrderController().placeOrder(order,new Customer(txtCustomerID.getText(),txtCustomerName.getText(),txtCustomerEmail.getText(),txtCustomerContact.getText()))){
                new Alert(Alert.AlertType.INFORMATION,"Placed Order!").show();

            }else {
                new Alert(Alert.AlertType.ERROR,"Order not Placed!!!").show();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(order);
    }

    @FXML
    public void btnAddCustomerOnAction(ActionEvent actionEvent) {

    }
    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        Customer customer=CustomerServiceImpl.getInstance().searchCustomerByID(txtCustomerID.getText());
        if(customer!=null){
            txtCustomerEmail.setText(customer.getEmail());
            txtCustomerContact.setText(customer.getCotact());
            txtCustomerName.setText(customer.getName());
        }
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {

    }
}
