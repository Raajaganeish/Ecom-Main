package com.Angular.UserDetails;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserBillAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7111336210447349500L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String AddressName;
	private String AddressSt1;
	private String AddressSt2;
	private String AddressCity;
	private String AddressCountry;
	private String addressZipCode;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	private UserPayment userPayment;

	public int getId() {
		return id;
	}

	

	public String getAddressName() {
		return AddressName;
	}

	public void setAddressName(String addressName) {
		AddressName = addressName;
	}

	public String getAddressSt1() {
		return AddressSt1;
	}

	public void setAddressSt1(String addressSt1) {
		AddressSt1 = addressSt1;
	}

	public String getAddressSt2() {
		return AddressSt2;
	}

	public void setAddressSt2(String addressSt2) {
		AddressSt2 = addressSt2;
	}

	public String getAddressCity() {
		return AddressCity;
	}

	public void setAddressCity(String addressCity) {
		AddressCity = addressCity;
	}

	public String getAddressCountry() {
		return AddressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		AddressCountry = addressCountry;
	}

	public String getAddressZipCode() {
		return addressZipCode;
	}

	public void setAddressZipCode(String addressZipCode) {
		this.addressZipCode = addressZipCode;
	}

	public UserPayment getUserPayment() {
		return userPayment;
	}

	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}

	@Override
	public String toString() {
		return "UserBillAddress [id=" + id + ", AddressName=" + AddressName + ", AddressSt1=" + AddressSt1
				+ ", AddressSt2=" + AddressSt2 + ", AddressCity=" + AddressCity + ", AddressCountry=" + AddressCountry
				+ ", addressZipCode=" + addressZipCode + ", userPayment=" + userPayment + "]";
	}
	
	
	
	
	
	

}
