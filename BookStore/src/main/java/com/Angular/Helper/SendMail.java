package com.Angular.Helper;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.Angular.CartEntities.CartItem;
import com.Angular.OrderEntities.Order;
import com.Angular.UserDetails.User;

import javax.activation.*;

public class SendMail {
	
	
	
	

    public static void Send_Password(String name,String email,String pwd)
    {
    	final String username = "raaja.sekar96@gmail.com";
        final String password = "virus13b";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("raaja.sekar96@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Registered Successfully");
            message.setText("Login with following Credentials "
                    + "\n\n and edit your personal information"+
                        "\n  Username: "+name
                        +"\n Password: "+pwd);

            Transport.send(message);

            System.out.println("Done");


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void OrderConfiramtion(User user,List<CartItem>ctItems,Order order)
    {
    	final String username = "raaja.sekar96@gmail.com";
        final String password = "virus13b";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("raaja.sekar96@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail())
            );
            message.setSubject("Order Confirmation");
            message.setSentDate(new Date());
            
            String cnfMsg = "<p> Hello <b>"+user.getFirstname()+"<b></p>"+"<p><i>Your order has been Placed Successfully!!<i></p>"
            					+"<p>Order Id"+order.getOrderId()+"</p>"
            					+"<p>Ordered On:"+order.getOrderDate()+"</p>"
            					+"<p>List of Items!</p>"
            					+"<table>" + 
            					"      <thead>" + 
            					"        <tr>" + 
            					"          <th>Id</th>" + 
            					"          <th>Name</th>" + 
            					"          <th>Price</th>" + 
            					"          <th>Qty</th>" + 
            					"        </tr>" + 
            					"      </thead>" + 
            					"      <tbody>" + 
            					"        <tr>";
            
            String listofBK="";
            
            for (CartItem cartItem : ctItems) {
            	listofBK += "<th>"+cartItem.getBook().getId()+"</th>"
            				+ "<th>"+cartItem.getBook().getTitle()+"</th>"
            				+"<th>"+cartItem.getQty()+"</th>"
            				+"<th>"+cartItem.getSubTotal()+"</th>"
            				+"<br>";
			}
            
            String finalMsgString = cnfMsg+listofBK+"<br>"+"<p>Grand Total is <b>"+order.getOrderTotal()+"</b></p>";

            message.setContent(finalMsgString, "text/html");
            
            Transport.send(message);

            System.out.println("Done");


        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
    	
    }
    
}
