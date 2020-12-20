package com.Angular.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angular.CartEntities.CartItem;
import com.Angular.CartEntities.ShoppingCart;
import com.Angular.JPA.CartItemJPA;
import com.Angular.JPA.OrderJPA;
import com.Angular.JPA.OrderPaymentJPA;
import com.Angular.JPA.OrderShippingAddressJPA;
import com.Angular.OrderEntities.Order;
import com.Angular.OrderEntities.Payment;
import com.Angular.OrderEntities.ShippingAddress;
import com.Angular.UserDetails.User;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderJPA orderJPA;
	
	@Autowired
	private OrderPaymentJPA orderPaymentJPA;
	
	@Autowired
	private OrderShippingAddressJPA orderShippingAddressJPA;
	
	@Autowired
	private CartItemJPA cartItemJPA;

	@Override
	public Order newOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, Payment payment, User user) {
		// TODO Auto-generated method stub
		
		Order order = new Order();
		order.setShippingAddress(shippingAddress);
		order.setPayment(payment);
		
		List<CartItem> ctItems = cartItemJPA.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : ctItems) {
			cartItem.setOrder(order);
		}
		
		order.setCartItemList(ctItems);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotoal());
		order.setOrderId(UUID.randomUUID().toString());
		shippingAddress.setOrder(order);
		payment.setOrder(order);
		
		order.setUser(user);
		
		order = orderJPA.save(order);
		
		
		return order;
	}

	@Override
	public Order findById(Integer id) {
		// TODO Auto-generated method stub
		return orderJPA.findById(id).get();
	}

	@Override
	public List<Order> findByUser(User user) {
		// TODO Auto-generated method stub
		return orderJPA.findByUser(user);
	}

}
