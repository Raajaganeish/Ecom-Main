package com.Angular.JPA;

import com.Angular.UserDetails.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJPA extends JpaRepository<User,Integer> {


    public User findByUsername(String username);

    public User findByEmail(String email);
    
    
}
