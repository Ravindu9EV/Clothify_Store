package service.custom;

import dto.Cart;
import dto.Order;
import service.SuperService;

import javax.mail.Session;
import java.util.List;

public interface EmailService extends SuperService {
    boolean sendRecipt(String orderID, List<Cart>carts, String sender, String reciver, Double total);

}
