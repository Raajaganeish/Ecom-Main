package com.Angular.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angular.CartEntities.CartItem;
import com.Angular.CartEntities.ShoppingCart;
import com.Angular.JPA.BookToCartJPA;
import com.Angular.JPA.CartItemJPA;
import com.Angular.JPA.ShoppingCartJPA;

@Service
public class ShoppingCartImpl implements ShoppingCartInter {
	
	
	@Autowired
	private CartItemJPA cartItemJPA;
	
	@Autowired
	private BookToCartJPA bookToCartJPA;
	
	@Autowired
	private ShoppingCartJPA shoppingCartJPA;

	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		
		BigDecimal gdTotal = new BigDecimal(0); 
		
		
		List<CartItem> cartItems = cartItemJPA.findByShoppingCart(shoppingCart);
		for (CartItem cartItem : cartItems) {
			
			gdTotal = gdTotal.add((cartItem.getSubTotal()));
		}
		
		shoppingCart.setGrandTotoal(gdTotal);
		shoppingCartJPA.save(shoppingCart);
		
		return shoppingCart;
	}

	@Override
	public void clearShoppingCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub

		List<CartItem> cartItems = cartItemJPA.findByShoppingCart(shoppingCart);
//		for (CartItem cartItem : cartItems) {
//			
//			cartItem.setShoppingCart(null);
//			bookToCartJPA.delete(bookToCartJPA.findByCartItem(cartItem));
//			
//			
//		}
		if(cartItems.size()>0)
		{
			cartItems.forEach(item->{item.setShoppingCart(null);cartItemJPA.save(item);});		
			
		}
		shoppingCart.setGrandTotoal(new BigDecimal(0));
		shoppingCartJPA.save(shoppingCart);
	}

}
