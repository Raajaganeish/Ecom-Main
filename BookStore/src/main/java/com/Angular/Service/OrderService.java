package com.Angular.Service;

import java.util.List;

import com.Angular.CartEntities.ShoppingCart;
import com.Angular.OrderEntities.Order;
import com.Angular.OrderEntities.Payment;
import com.Angular.OrderEntities.ShippingAddress;
import com.Angular.UserDetails.User;

public interface OrderService {
	
	Order newOrder(ShoppingCart shoppingCart,ShippingAddress shippingAddress,Payment payment,User user);
	Order findById(Integer id);
	List<Order> findByUser(User user);
}
