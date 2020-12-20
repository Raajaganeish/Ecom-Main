package com.Angular.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Angular.OrderEntities.Order;
import com.Angular.UserDetails.User;
import java.util.List;
import java.util.Optional;

public interface OrderJPA extends JpaRepository<Order, Integer> {
	
	List<Order> findByUser(User user);

	Optional<Order> findById(Integer id);

}
