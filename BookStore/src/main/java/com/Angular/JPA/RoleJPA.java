package com.Angular.JPA;

import com.Angular.UserDetails.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJPA extends JpaRepository<Role,Integer> {
	
	Role findByName(String name);
}
