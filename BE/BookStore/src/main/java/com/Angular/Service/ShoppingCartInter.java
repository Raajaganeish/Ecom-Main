package com.Angular.Service;

import com.Angular.CartEntities.ShoppingCart;

public interface ShoppingCartInter {
	
	
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);

}
