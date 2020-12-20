package com.Angular.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angular.CartEntities.BookToCart;
import com.Angular.CartEntities.CartItem;
import com.Angular.CartEntities.ShoppingCart;
import com.Angular.Entity.Book;
import com.Angular.JPA.BookToCartJPA;
import com.Angular.JPA.CartItemJPA;
import com.Angular.UserDetails.User;

import javassist.NotFoundException;

@Service
public class CartItemImpl implements CartItemInterface {
	
	@Autowired
	private CartItemJPA cartItemJPA;
	
	@Autowired
	private BookToCartJPA bookToCartJPA;

	@Override
	public CartItem addBookIntoCart(Book bk, User user, int qty) {
		// TODO Auto-generated method stub
		
		List<CartItem> cartItems = cartItemJPA.findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartitem : cartItems) {
			
			if(cartitem!=null)
			{
				if(cartitem.getBook().getId()==bk.getId())
				{
					cartitem.setQty(qty);
					cartitem.setSubTotal(new BigDecimal(bk.getPrice()).multiply(new BigDecimal(qty)));
					cartItemJPA.save(cartitem);
					return cartitem;
				}
			}
		}
		
		
		CartItem cartItem_obj = new CartItem();
		cartItem_obj.setBook(bk);
		cartItem_obj.setShoppingCart(user.getShoppingCart());
		cartItem_obj.setQty(qty);
		cartItem_obj.setSubTotal(new BigDecimal(bk.getPrice()).multiply(new BigDecimal(qty)));
		
		cartItem_obj = cartItemJPA.save(cartItem_obj);
		
		BookToCart bk_obj = new BookToCart();
		bk_obj.setBook(bk);
		bk_obj.setCartItem(cartItem_obj);
		bookToCartJPA.save(bk_obj);
		
		
		
		
		
		return cartItem_obj;
	}

	@Override
	public void removeCartItem(Integer id) {
		// TODO Auto-generated method stub
		Optional<CartItem> cartItem_obj = cartItemJPA.findById(id);
		CartItem cartItem = cartItem_obj.get();
		bookToCartJPA.delete(bookToCartJPA.findByCartItem(cartItem));
		cartItemJPA.delete(cartItem);
	}

	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		return cartItemJPA.findByShoppingCart(shoppingCart);
	}

	@Override
	public CartItem updateCartItem(Integer id, Integer qty) {
		// TODO Auto-generated method stub
		Optional<CartItem> ctItem = cartItemJPA.findById(id);
		CartItem ctCartItem = ctItem.get();
		ctCartItem.setQty(qty);
		ctCartItem.setSubTotal(new BigDecimal(ctCartItem.getBook().getPrice()).multiply(new BigDecimal(qty)));
		cartItemJPA.save(ctCartItem);
		return ctCartItem;
	}

	@Override
	public void flushCart(List<CartItem> ctItems) {
		
		ctItems.forEach(item->{bookToCartJPA.delete(bookToCartJPA.findByCartItem(item));});
		cartItemJPA.deleteAll(ctItems);
		
	}

}
