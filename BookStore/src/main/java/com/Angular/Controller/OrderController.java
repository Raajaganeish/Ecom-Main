package com.Angular.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Angular.CartEntities.CartItem;
import com.Angular.CartEntities.ShoppingCart;
import com.Angular.Helper.SendMail;
import com.Angular.OrderEntities.Order;
import com.Angular.OrderEntities.Payment;
import com.Angular.OrderEntities.ShippingAddress;
import com.Angular.Service.CartItemInterface;
import com.Angular.Service.OrderService;
import com.Angular.Service.ShoppingCartInter;
import com.Angular.Service.UserService;
import com.Angular.UserDetails.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private CartItemInterface cartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShoppingCartInter shoppingService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/checkout")
	private Order checkOutOrder(@RequestBody HashMap<String, Object>hmap,Principal principal)
	{
		ObjectMapper mapper = new ObjectMapper();
		ShippingAddress shippingAddress = mapper.convertValue(hmap.get("shippingAddress"), ShippingAddress.class);
		Payment payment = mapper.convertValue(hmap.get("payment"), Payment.class);
		
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		System.out.println(shippingAddress.getShippingAddressZipCode());
		System.out.println(payment.getCcv());
		
		Order order = orderService.newOrder(shoppingCart, shippingAddress, payment, user);
		
		List<CartItem> ctItems = cartItemService.findByShoppingCart(shoppingCart);
		
		SendMail.OrderConfiramtion(user,ctItems,order);
		
		System.out.println("Emptying Shopping Cart!!!");
		
		shoppingService.clearShoppingCart(shoppingCart);
		
		return order;
	}
	
	
	@GetMapping("/getAllOrder")
	public List<Order> getAllOrders(Principal principal)
	{
		List<Order> orders = orderService.findByUser(userService.findByUsername(principal.getName()));
		return orders;
	}
	

}
