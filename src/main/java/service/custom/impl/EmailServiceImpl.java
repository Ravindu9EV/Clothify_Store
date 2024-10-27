package service.custom.impl;

import com.sun.mail.smtp.SMTPTransport;
import dto.Cart;
import dto.Order;
import javafx.scene.control.Alert;
import service.custom.EmailService;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailServiceImpl implements EmailService {
   private static boolean send=false;
    @Override
    public boolean sendRecipt(String orderID, List<Cart>cartList, String sender, String receiver, Double total) {
        String subject=orderID+"Receipt" ;
        String body="";
        String tot=""+total;
        for(Cart cart:cartList){
            String row=String.format("| %-8s | %-10s | %-12s | %-14s | %-10f |\n",cart.getProductID(),cart.getProductPrice(),cart.getProductQuantity(),cart.getProductDiscount(),total);
            body+=row;
        }
        System.out.println(body);
        String host="127.0.0.1";

        Properties properties=System.getProperties();


        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port","587");
       //Session session=Session.getDefaultInstance(properties);
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("\n" +
                        "yravindu18@gmail.com","dgoe bnmm oiwb yrrc");
            }
        });
        Message message = sendEmail(session,receiver,subject,body);
        if(message!=null){
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return true;
        }else {
            return false;
        }

    }

    private Message sendEmail(Session session, String receiver, String subject, String body){
        //set headers

        try {

            Message msg=new MimeMessage(session);
//            msg.addHeader("Content-type","text/HTML; charset=UTF-8");
//            msg.addHeader("format","flowed");
//            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("yravindu18@gmail.com","NoReply-JD"));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receiver));

            //msg.setSubject(subject,"UTF-8");
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            MimeBodyPart mimeBodyPart= new MimeBodyPart();
            mimeBodyPart.setContent(body,"text/html; charset=utf-8");

            Multipart multipart=new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);



            msg.setContent(multipart);



            System.out.println("email is ready!");
            System.out.println(msg.toString());
           return msg;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
