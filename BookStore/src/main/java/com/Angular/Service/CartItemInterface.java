package com.Angular.Service;

import java.util.List;

import com.Angular.CartEntities.CartItem;
import com.Angular.CartEntities.ShoppingCart;
import com.Angular.Entity.Book;
import com.Angular.UserDetails.User;

public interface CartItemInterface {

	CartItem addBookIntoCart(Book bk,User user,int qty);
	
	void removeCartItem(Integer id);
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(Integer id,Integer qty);
	
	void flushCart(List<CartItem> ctItems);
}
