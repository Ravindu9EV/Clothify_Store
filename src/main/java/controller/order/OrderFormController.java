package controller.order;

import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.ServiceFactory;
import service.SuperService;
import service.custom.CustomerService;
import service.custom.OrderService;
import service.custom.ProductService;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.ProductServiceImpl;
import util.DaoType;
import util.ServiceType;
import org.modelmapper.ModelMapper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController  implements Initializable {

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
    public ComboBox<String> combProductID;
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
    private ComboBox<String> combProductCategory;

    @FXML
    private ComboBox<String> combProductSize;

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
    private OrderService orderService=ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
    @FXML
    void btnAddToOrderOnAction(ActionEvent event) {

        String orderID=lblOrderID.getText();
        String productID= combProductID.getValue().toString();
        Integer productQuantity=Integer.parseInt(txtProductQuantity.getText());
        Double productPrice=Double.parseDouble(txtProductPrice.getText());
        Double productDiscount=Double.parseDouble(txtProductDiscount.getText());
        Double totalPrice=(productPrice-productDiscount)*productQuantity;


        //-----------Customer Table------------


        if(productQuantity<=Integer.parseInt(txtProductStock.getText())){

            cart.add(new Cart(productID,productPrice,productQuantity,productDiscount,totalPrice));
            tblOrder.setItems(cart);
            calcTotal();
        }



    }

    private void loadProductIDs() throws SQLException {
        ObservableList<String> productIDs= FXCollections.observableArrayList();
        ProductDao productDao= DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
                productDao.findAll().forEach(productEntity -> {
                    String pID=new ModelMapper().map(productEntity,Product.class).getId();
                    productIDs.add(pID);
                    System.out.println(pID);
                });

                combProductID.setItems(productIDs);
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

        OrderService serviceType=ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);

        try {
            if(serviceType.placeOrder(order)){
                CustomerService service=ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
                if(service.addCustomer(new Customer(txtCustomerID.getText(),txtCustomerName.getText(),txtCustomerEmail.getText(),txtCustomerContact.getText()))){
                    new Alert(Alert.AlertType.INFORMATION,"Placed Order!").show();
                    Stage stage=new Stage();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/customer_form.fxml"))));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

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
        CustomerService service=ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
        Customer customer=service.searchCustomerByID(txtCustomerID.getText());
        if(txtCustomerID.getText().equals("")){
          customer=service.searchCustomerByEmail(txtCustomerEmail.getText());
        }

        if(customer!=null){
            txtCustomerEmail.setText(customer.getEmail());
            txtCustomerContact.setText(customer.getCotnact());
            txtCustomerName.setText(customer.getName());
        }
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException {
        setProductIDs();
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProductIDs();
        setOrderTableCols();
        //------------Load Cart table---------
        ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        productService.getAllProducts().forEach(product -> {
//            System.out.println(product);
        });
        //-------------Load Product Categories------------
        setProductCategory();
        setProductSize();

        setCustomerTableCols();
    }

    private void setProductIDs() throws SQLException {
        ObservableList<String> productIDs=FXCollections.observableArrayList();
        ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        productService.getAllProducts().forEach(product -> {
            System.out.println(product);
        });
    }

    private void setProductSize() {
        ObservableList<String> size=FXCollections.observableArrayList();

        size.add("S");
        size.add("M");
        size.add("L");
        size.add("XL");

        combProductSize.setItems(size);
    }

    private void setProductCategory() {
        ObservableList<String> category=FXCollections.observableArrayList();
        category.add("Ladies");
        category.add("Gents");
        category.add("Kids");
        combProductCategory.setItems(category);
    }

    private void addValueToText(Product product) {
        txtProductStock.setText(product.getQuantity().toString());
        txtProductPrice.setText(product.getPrice()+"");

    }

    private void searchProduct(String id) {
        ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        System.out.println(productService.searchProduct(id));
        addValueToText(productService.searchProduct(id));
    }
    //------------Set Cart table Columns---------
    private void setOrderTableCols(){



//        colOrderProductID.setCellValueFactory(new PropertyValueFactory<>(productID));
//        colOrderProductPrice.setCellValueFactory(new PropertyValueFactory<>(productPrice.toString()));
//        colOrderProductQuantity.setCellValueFactory(new PropertyValueFactory<>(productQuantity.toString()));
//        colOrderProductDiscount.setCellValueFactory(new PropertyValueFactory<>(productDiscount.toString()));
//        colOrderProductTotalCost.setCellValueFactory(new PropertyValueFactory<>(totalPrice.toString()));


        colOrderProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colOrderProductPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        colOrderProductQuantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        colOrderProductDiscount.setCellValueFactory(new PropertyValueFactory<>("productDiscount"));
        colOrderProductTotalCost.setCellValueFactory(new PropertyValueFactory<>("productTotalCost"));
    }

    //---------------Set Customer Table Columns--------------------
    private void setCustomerTableCols(){
        colProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("contact"));


    }

    public void loadOrderTable() throws SQLException {
        ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        orderService.getAllOrders().forEach(order ->{
            order.getOrderDetails().forEach(orderDetail -> {

                Product product=productService.searchProduct(orderDetail.getProductID());
                double unitPrice= product.getPrice();
                int quantity= product.getQuantity();
                double discount=orderDetail.getDiscount();
                cart.add (new Cart(orderDetail.getProductID(),unitPrice,quantity,discount,((unitPrice-discount)*quantity)));
            });
        } );
        tblOrder.setItems(cart);
    }
}
