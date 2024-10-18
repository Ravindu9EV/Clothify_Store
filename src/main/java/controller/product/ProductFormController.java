package controller.product;

import com.jfoenix.controls.JFXTextField;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.ServiceFactory;
import service.custom.ProductService;
import util.DaoType;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {

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
    private TableView<Product> tblProducts;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtProductPrice;

    @FXML
    private JFXTextField txtProductQuantity;

    private ProductService service=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    //--------------------Add Product -----------------
    @FXML
    void btnAddOnAction(ActionEvent event) {
        ProductService productService= ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

        try{
            try {
                try{

//                    System.out.println(txtProductId.getText());
//                    System.out.println(txtProductName.getText());
//                    // System.out.println(combProductSize.getValue().toString());
//                    System.out.println(Double.parseDouble(txtProductPrice.getText()));
//                    System.out.println(Integer.parseInt(txtProductQuantity.getText()));
//                    System.out.println(combProductCategory.getValue().toString());
//                    System.out.println("jdaljda");

                    Product product=getTxtValues();
                    System.out.println(product);
                    productService.addProduct(product);
                    new Alert(Alert.AlertType.INFORMATION,"Successfully Added!").show();
                    loadProductTable();
                }catch (NumberFormatException e){
                    throw new NumberFormatException();
                }
            }
            catch(NullPointerException e) {
                throw new NullPointerException();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Somthing Wrong!\n"+e).show();
        }
    }

    //--------------------Remove Product -----------------
    @FXML
    void btnRemoveOnAction(ActionEvent event) {

        if(service.deleteProduct(txtProductId.getText().toString())){
            loadProductTable();
            new Alert(Alert.AlertType.INFORMATION,"Deleted...").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    //--------------------Search Product -----------------
    @FXML
    void btnSearchOnAction(ActionEvent event) {

        try{
            Product product=service.searchProduct(txtProductId.getText());
                addValueToTxt(product);



        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Not Found!\n"+e).show();
        }
    }

    //--------------------Update Product -----------------
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try{
            service.updateProduct(getTxtValues());

            loadProductTable();
            new Alert(Alert.AlertType.INFORMATION,"Updated Successfully...").show();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Oops!\n"+e).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProductSize();
        setProductCotegory();
        colProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        loadProductTable();

        tblProducts.getSelectionModel().selectedItemProperty().addListener((observableValue, product, newProduct) -> {
            if(newProduct!=null){
                addValueToTxt(newProduct);
            }
        });
    }

    private void addValueToTxt(Product product) {
        txtProductId.setText(product.getId());
        txtProductName.setText(product.getName());
        txtProductPrice.setText(product.getPrice()+"");
        txtProductQuantity.setText(product.getQuantity()+"");
        combProductSize.setValue(product.getSize());
        combProductCategory.setValue(product.getCategory());
    }

    public Product getTxtValues(){
        return new Product(txtProductId.getText(),txtProductName.getText(),combProductSize.getValue().toString(),Double.parseDouble(txtProductPrice.getText()),Integer.parseInt(txtProductQuantity.getText()),combProductCategory.getValue().toString(),"");
    }

    private void setProductCotegory() {
        ObservableList<String> categories=FXCollections.observableArrayList();
        categories.add("Ladies");
        categories.add("Gents");
        categories.add("Kids");
        combProductCategory.setItems(categories);
    }

    private void loadProductTable() {
        ObservableList<Product> productObservableList= FXCollections.observableArrayList();
        ProductDao productDao= DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        ProductService ps=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        try {
            System.out.println(productDao.findAll());
            System.out.println(ps.getAllProducts());
           service.getAllProducts().forEach(product -> {
               productObservableList.add(product);
           });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        tblProducts.setItems(productObservableList);
    }

    public void setProductSize(){
        ObservableList<String> sizes=FXCollections.observableArrayList();
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");

        combProductSize.setItems(sizes);
    }

}
