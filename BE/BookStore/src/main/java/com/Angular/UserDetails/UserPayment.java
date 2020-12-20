package com.Angular.UserDetails;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserPayment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5077048508434044238L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String holderName;
	private String cardName;
	private String cardNumber;
	private String cardType;
	private String expiryDate;
	private String expiryMonth;
	private int ccv;
	private boolean isDefault;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="userPayment")
	private UserBillAddress userBillAddress;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getCcv() {
		return ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserBillAddress getUserBillAddress() {
		return userBillAddress;
	}

	public void setUserBillAddress(UserBillAddress userBillAddress) {
		this.userBillAddress = userBillAddress;
	}

	@Override
	public String toString() {
		return "UserPayment [id=" + id + ", holderName=" + holderName + ", cardName=" + cardName + ", cardNumber="
				+ cardNumber + ", cardType=" + cardType + ", expiryDate=" + expiryDate + ", expiryMonth=" + expiryMonth
				+ ", ccv=" + ccv + ", isDefault=" + isDefault + ", user=" + user + ", userBillAddress="
				+ userBillAddress + "]";
	}
	
	
	
}
