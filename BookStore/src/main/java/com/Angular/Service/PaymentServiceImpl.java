package com.Angular.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angular.JPA.PaymentJPA;
import com.Angular.JPA.UserJPA;
import com.Angular.UserDetails.User;
import com.Angular.UserDetails.UserBillAddress;
import com.Angular.UserDetails.UserPayment;

@Service
public class PaymentServiceImpl implements PaymentServiceInt {
	
	@Autowired
	private PaymentJPA payment_jpa;
	
	@Autowired
	private UserJPA user_jpa;

	@Override
	public List<UserPayment> findByUser(User usr) {
		// TODO Auto-generated method stub
		return payment_jpa.findByUser(usr);
	}

	@Override
	public void updatePaymentDetails(UserBillAddress userBillAddress, UserPayment userPayment, User user) {
		
		userPayment.setUser(user);
		userPayment.setUserBillAddress(userBillAddress);
		userPayment.setDefault(true);
		
		userBillAddress.setUserPayment(userPayment);
		
		user.getUserPayments().add(userPayment);
		
		user_jpa.save(user);
	}

	@Override
	public void removeById(int parseInt) {
		// TODO Auto-generated method stub
		payment_jpa.deleteById(parseInt);
	}

	@Override
	public void saveAllinList(List<UserPayment> payments,User user) {
		// TODO Auto-generated method stub
		payments.forEach(paymentObject->{
			
			System.out.println(paymentObject.isDefault());
			UserBillAddress billAddressObj = paymentObject.getUserBillAddress();
			paymentObject.setUser(user);
			paymentObject.setUserBillAddress(billAddressObj);
			
			billAddressObj.setUserPayment(paymentObject);
			
			
		});
		
		user.setUserPayments(payments);
		user_jpa.save(user);
	}

	
	
}
