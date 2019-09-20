package com.Angular.CartEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.Angular.Entity.Book;
import com.Angular.OrderEntities.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -801371130048099526L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int qty;
	private BigDecimal subTotal;
	
	@OneToOne
	private Book book;
	
	@OneToMany(mappedBy="cartItem")
	@JsonIgnore
	private List<BookToCart> booktoCartItemList;
	
	@ManyToOne
	@JoinColumn(name="shopping_cart_id")
	@JsonIgnore
	private ShoppingCart shoppingCart;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	@JsonIgnore
	private Order order;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<BookToCart> getBooktoCartItemList() {
		return booktoCartItemList;
	}

	public void setBooktoCartItemList(List<BookToCart> booktoCartItemList) {
		this.booktoCartItemList = booktoCartItemList;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", qty=" + qty + ", subTotal=" + subTotal + ", book=" + book.toString() + "sCart = " + shoppingCart.toString();
//				+ ", booktoCartItemList=" + booktoCartItemList + ", shoppingCart=" + shoppingCart + ", order=" + order
//				+ "]";
	}

	
	
	
	
}
