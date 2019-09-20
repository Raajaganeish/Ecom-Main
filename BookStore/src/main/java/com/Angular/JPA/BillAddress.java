package com.Angular.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Angular.UserDetails.UserBillAddress;

public interface BillAddress extends JpaRepository<UserBillAddress, Integer> {

}
