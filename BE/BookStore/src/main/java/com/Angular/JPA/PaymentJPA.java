package com.Angular.JPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Angular.UserDetails.User;
import com.Angular.UserDetails.UserPayment;

public interface PaymentJPA extends JpaRepository<UserPayment, Integer> {
	
	List<UserPayment>  findByUser(User user);
}
