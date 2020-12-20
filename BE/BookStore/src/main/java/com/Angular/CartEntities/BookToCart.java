package com.Angular.CartEntities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.Angular.Entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class BookToCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1122095865673588915L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="cartItem_id")
	private CartItem cartItem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public String toString() {
		return "BookToCart [id=" + id + ", book=" + book + ", cartItem=" + cartItem + "]";
	}
	
	

}
