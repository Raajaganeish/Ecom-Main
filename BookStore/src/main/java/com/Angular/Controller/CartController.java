package com.Angular.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Angular.CartEntities.CartItem;
import com.Angular.CartEntities.ShoppingCart;
import com.Angular.Entity.Book;
import com.Angular.Service.BookService;
import com.Angular.Service.CartItemImpl;
import com.Angular.Service.CartItemInterface;
import com.Angular.Service.ShoppingCartInter;
import com.Angular.Service.UserService;
import com.Angular.UserDetails.User;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartItemInterface cartItemService;
	
	@Autowired
	private ShoppingCartInter shoppingCartInterService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/addItem")
	public ResponseEntity<String> addItem(@RequestBody HashMap<String, String> hmap,Principal principal)
	{
		String bkId = hmap.get("bookId");
		String qty =  hmap.get("qty");
		
		System.out.println(bkId+"\t"+qty);
		
		User user = userService.findByUsername(principal.getName());
		
		Optional<Book> book = bookService.findById(Integer.parseInt(bkId));
		
		System.out.println("===>"+book.get().toString());
		
		CartItem cartItem = cartItemService.addBookIntoCart(book.get(), user, Integer.parseInt(qty));
		
		
		return new ResponseEntity<String>("Book Addes Into Cart", HttpStatus.OK);
	}
	
	@GetMapping("/getAllCartItems")
	public List<CartItem> getAll(Principal principal)
	{
		User user = userService.findByUsername(principal.getName());
		
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItems = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartInterService.updateShoppingCart(shoppingCart);
		
		return cartItems;
	}
	
	
	@PostMapping("/updateCart")
	public ResponseEntity<String> updateCart(@RequestBody HashMap<String, String> hmap)
	{
		String c_id = hmap.get("cartItemId");
		String qty = hmap.get("qty");
		
		CartItem res = cartItemService.updateCartItem(Integer.parseInt(c_id), Integer.parseInt(qty));
		
				
		return new ResponseEntity<String>("Updates Successfully", HttpStatus.OK);
	}
	
	
	@PostMapping("/remove")
	public ResponseEntity<String> removeCart(@RequestBody String id)
	{
		cartItemService.removeCartItem(Integer.parseInt(id));
		return new ResponseEntity<String>("Removed Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/removeAll")
	public ResponseEntity<String> removeAllCartItem(Principal principal)
	{
		User user = userService.findByUsername(principal.getName());
		
		List<CartItem> ctItems = cartItemService.findByShoppingCart(user.getShoppingCart());
		
		ctItems.forEach((cartItem)->{
			System.out.println(""+cartItem.toString());
			
		});
		
		cartItemService.flushCart(ctItems);
		
		return new ResponseEntity<String>("Removed Successfully", HttpStatus.OK);
	}
	
	
	@GetMapping("/getShoppingCart")
	public ShoppingCart getShoppingCart(Principal principal)
	{
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		shoppingCartInterService.updateShoppingCart(shoppingCart);
		
		return shoppingCart;
	}
	
	

}
