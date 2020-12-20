package com.Angular.OrderEntities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ShippingAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6027760207249967075L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String shippingAddressName;
	private String shippingAddressSt1;
	private String shippingAddressSt2;
	private String shippingAddressCity;
	private String shippingAddressCountry;
	private String shippingAddressZipCode;
	
	@OneToOne
	@JsonIgnore
	private Order order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShippingAddressName() {
		return shippingAddressName;
	}

	public void setShippingAddressName(String shippingAddressName) {
		this.shippingAddressName = shippingAddressName;
	}

	public String getShippingAddressSt1() {
		return shippingAddressSt1;
	}

	public void setShippingAddressSt1(String shippingAddressSt1) {
		this.shippingAddressSt1 = shippingAddressSt1;
	}

	public String getShippingAddressSt2() {
		return shippingAddressSt2;
	}

	public void setShippingAddressSt2(String shippingAddressSt2) {
		this.shippingAddressSt2 = shippingAddressSt2;
	}

	public String getShippingAddressCity() {
		return shippingAddressCity;
	}

	public void setShippingAddressCity(String shippingAddressCity) {
		this.shippingAddressCity = shippingAddressCity;
	}

	public String getShippingAddressCountry() {
		return shippingAddressCountry;
	}

	public void setShippingAddressCountry(String shippingAddressCountry) {
		this.shippingAddressCountry = shippingAddressCountry;
	}

	public String getShippingAddressZipCode() {
		return shippingAddressZipCode;
	}

	public void setShippingAddressZipCode(String shippingAddressZipCode) {
		this.shippingAddressZipCode = shippingAddressZipCode;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "ShippingAddress [id=" + id + ", shippingAddressName=" + shippingAddressName + ", shippingAddressSt1="
				+ shippingAddressSt1 + ", shippingAddressSt2=" + shippingAddressSt2 + ", shippingAddressCity="
				+ shippingAddressCity + ", shippingAddressCountry=" + shippingAddressCountry
				+ ", shippingAddressZipCode=" + shippingAddressZipCode + ", order=" + order + "]";
	}
	
	
	

}
