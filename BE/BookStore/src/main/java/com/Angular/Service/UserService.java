package com.Angular.Service;

import com.Angular.Entity.Book;
import com.Angular.UserDetails.Role;
import com.Angular.UserDetails.User;
import com.Angular.UserDetails.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {


    public User createUser(User usr_obj, Set<UserRole> ur_role);

    public User findByEmail(String email);
    
    public Optional<User> findById(Integer id);

    public void setNewPassword(User user,String password);
    
    public Role findRoleByName(String name);
    
    public User findByUsername(String username);
    
    public User SetPasswordAlone(User user,String password);
    
    public void save(User ur);
    
    public List<User> findAll();
}
