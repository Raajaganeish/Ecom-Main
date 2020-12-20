package com.Angular.Service;

import java.util.List;

import com.Angular.UserDetails.User;
import com.Angular.UserDetails.UserBillAddress;
import com.Angular.UserDetails.UserPayment;

public interface PaymentServiceInt {
	
	List<UserPayment> findByUser(User usr);

	void updatePaymentDetails(UserBillAddress userBillAddress, UserPayment userPayment, User user);

	void removeById(int parseInt);
	
	void saveAllinList(List<UserPayment> payments,User user);

}
