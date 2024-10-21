package controller.order;

import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.binding.StringFormatter;
import dto.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import lombok.SneakyThrows;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.ServiceFactory;
import service.SuperService;
import service.custom.CustomerService;
import service.custom.OrderDetailService;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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
    public ComboBox combCustomerID;
    public TableColumn colCustomerID;

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
    ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    CustomerService customerService=ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    OrderDetailService orderDetailService=ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAIL);
    @FXML
    void btnAddToOrderOnAction(ActionEvent event) {

        try{
//

            //-----------Customer Table------------


            if(Integer.parseInt(txtProductQuantity.getText())<=Integer.parseInt(txtProductStock.getText())){
                //new Cart(productID,productPrice,productQuantity,productDiscount,totalPrice)
                cart.add(getCart());
                tblOrder.setItems(cart);
                calcTotal();
            }
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Oops!\n"+e).show();
        }



    }
//--------------get cart detail----------------------------
    private Cart getCart(){
        String pId=combProductID.getValue();
           if(pId!=null){
               Product product=productService.searchProduct(pId);
               //need to check stock is available
               Double totlCost= (product.getPrice()-Double.parseDouble(txtProductDiscount.getText()))* Integer.parseInt(txtProductQuantity.getText());

               Cart c=new Cart(
                       product.getId(),
                       product.getPrice(),
                       Integer.parseInt(txtProductQuantity.getText()),
                       Double.parseDouble(txtProductDiscount.getText()),
                       Double.parseDouble(String.format("%.2f",totlCost))
               );
               System.out.println(c);
               return c;
           }else {
               return null;
           }



    }
    private void setProductFields(Product product){
        txtProductStock.setText(product.getQuantity()+"");
        txtProductPrice.setText(product.getPrice()+"");
        combProductCategory.setValue(product.getCategory());
        combProductSize.setValue(product.getSize());
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


    private void loadCustomerIDs(){
        ObservableList<String> ids=FXCollections.observableArrayList();
        try {
            for(Customer customer:customerService.getAll()){
                ids.add(customer.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        combCustomerID.setItems(ids);
    }

    @FXML
    void btnRemoveFromOrderOnAction(ActionEvent event) {

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, cart1, t1) -> {
            if(t1!=null) {
                tblOrder.getSelectionModel().clearSelection(tblOrder.selectionModelProperty().hashCode());
            }
        });
        tblOrder.refresh();

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        System.out.println(lblDate.getText());
        String oderId=generateID();
        lblOrderID.setText(oderId);
        List<OrderDetail> orderDetails=new ArrayList<>();
        orderDetails.add(getOrderDetail());
//        Order order=null;
        try {
            if(orderService.placeOrder(new Order(oderId,lblUserID.getText(),txtCustomerID.getText(), LocalDate.parse(lblDate.getText()),combPaymentType.getValue().toString(),orderDetails))){
                if (orderDetailService.addOrderDetail(orderDetails)){
                    if(productService.updateStock(getOrderDetail())){
                        new Alert(Alert.AlertType.INFORMATION,"Placed Order!").show();
                    }
                }
            }
            new Alert(Alert.AlertType.ERROR).show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //set Date

    //--------Get Order----------
    public OrderDetail getOrderDetail(){
        //OrderDetail orderDetail=System.out.println(orderDetail);
        return new OrderDetail(lblOrderID.getText(),combProductID.getValue(),Integer.parseInt(txtProductQuantity.getText()),Double.parseDouble(txtProductDiscount.getText()));
    }
    //--------------set Payment Type
    private void setPaymentType(){

        ObservableList<String> paymentTypes=FXCollections.observableArrayList();
        paymentTypes.add("Online");
        paymentTypes.add("Credit Card");
        paymentTypes.add("Cash");
        combPaymentType.setItems(paymentTypes);
    }
    //--------------set customer text field -------------
    private void addValuesToCustomerTxtFields(Customer customer){
        //combCustomerID.setValue(customer.getId());
        txtCustomerID.setText(customer.getContact());
        txtCustomerName.setText(customer.getName());
        txtCustomerEmail.setText(customer.getEmail());
        txtCustomerContact.setText(customer.getContact());
    }

    //-----------Map customer table records with customer Text fields--------
    public void setCustomerTxtValue(){
        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, t1) -> {
            if(t1!=null){
                addValuesToCustomerTxtFields(t1);
            }
        });
    }



    //-----get Customer Object------------
    private Customer getCustomer(){
        return new Customer(combCustomerID.getValue().toString(),txtCustomerName.getText(),txtCustomerEmail.getText(),txtCustomerContact.getText());
    }

    @FXML
    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        customerService.addCustomer(getTxtCustomer());
        loadCustomerTable();
        System.out.println(getTxtCustomer());

    }
    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        try{
            Customer customer=customerService.searchCustomerByID(txtCustomerID.getText());
            if(txtCustomerID.getText().equals("")){
                customer=customerService.searchCustomerByEmail(txtCustomerEmail.getText());
            }

            if(customer!=null){
                addValueToCustomerTxtFields(customer);
            }
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Oops!\n"+e).show();
        }
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws SQLException {
        try {
            System.out.println(generateID());
        }catch (RuntimeException e){
            System.out.println(e);
        }
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(lblDate.getText());
        //----Set Date-------------
        Date newDate=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(dateFormat.format(newDate));


        //---------Set Time--------
        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO,actionEvent -> {
            LocalTime time=LocalTime.now();
            lblTime.setText(time.getHour()+":"+time.getMinute()+":"+time.getSecond());
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        setProductIDs();
        setOrderTableCols();
        setCustomerTableCols();
        loadProductIDs();
        setOrderID();
        setPaymentType();

        //------------Load Cart table---------
        loadOrderTable();
        if (!customerService.getAll().isEmpty()){
            loadCustomerTable();
        }
        //-------------Load Product Categories------------
        setProductCategory();
        setProductSize();
        combProductID.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if(t1!=null ){
                searchProduct(t1);
            }
        });
        combPaymentType.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if(t1!=null){
                combPaymentType.setValue(t1);
            }
        });

        setCustomerTxtValue();

        setCustomerTableCols();
    }



    private void setProductIDs() throws SQLException {
        ObservableList<String> productIDs=FXCollections.observableArrayList();

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

    private Product searchProduct(String id) {
        ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        Product product=productService.searchProduct(id);
        System.out.println(product);

        setProductFields(product);
        return product;
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
        colOrderProductTotalCost.setCellValueFactory(new PropertyValueFactory<>("productTotalPrice"));
    }

    //---------------Set Customer Table Columns--------------------
    private void setCustomerTableCols(){
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("contact"));


    }


    public void loadOrderTable() throws SQLException {
//        OrderService service=ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
//        service.getAllOrders().forEach(order -> {
//            order.
//        });

        tblOrder.setItems(cart);
        tblOrder.editableProperty();
    }

    public void btnCustomerFormOnAction(ActionEvent actionEvent) {
        Stage stage=new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/customer_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //-------------------Generate ID--------------------------
    public String generateID() {
        String code="";
        do{
            code=String.format("CS%06d", new Random().nextInt(9999) + 1);
        }while (orderService.search(code)!=null);
        return code;
    }

    public void btnGenerateIDOnAction(ActionEvent actionEvent) {
        setOrderID();
    }

    public void setOrderID(){
        lblOrderID.setText(generateID());
    }



    //------------------------------Customer Section--------------------------------------------




    //--------------------load Table--------------------------
    public void loadCustomerTable(){
        ObservableList<Customer> customers= FXCollections.observableArrayList();
        try {
            customerService.getAll().forEach(customer -> {
                customers.add(customer);
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tblCustomers.setItems(customers);
    }

    //------------------Add Selected Item values to Text Fields-----------------------
    public void addValueToCustomerTxtFields(Customer customer){
        txtCustomerID.setText(customer.getId());
        txtCustomerName.setText(customer.getName());
        txtCustomerEmail.setText(customer.getEmail());

        txtCustomerContact.setText(customer.getContact());
    }

    //-------------clear Text Fields----------------------------

    public void clearTxt(){
        txtCustomerID.clear();
        txtCustomerEmail.clear();
        txtCustomerName.clear();
        txtCustomerContact.clear();
    }

    public void btnClearTextOnAction(ActionEvent actionEvent) {
        clearTxt();
    }

    //--------------Get Text Fields Value---------------
    public Customer getTxtCustomer(){
        return new Customer(
                txtCustomerID.getText(),
                txtCustomerName.getText(),
                txtCustomerEmail.getText(),
                txtCustomerContact.getText()
        );
    }
}
