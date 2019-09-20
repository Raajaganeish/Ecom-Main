package com.Angular.CartEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.Angular.UserDetails.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ShoppingCart implements Serializable {

	
	private static final long serialVersionUID = 7645070361573946811L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private BigDecimal grandTotoal;
	
	@OneToMany(mappedBy="shoppingCart", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CartItem> cartItemList;
	
	@OneToOne
	@JsonIgnore
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getGrandTotoal() {
		return grandTotoal;
	}

	public void setGrandTotoal(BigDecimal grandTotoal) {
		this.grandTotoal = grandTotoal;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	

}
