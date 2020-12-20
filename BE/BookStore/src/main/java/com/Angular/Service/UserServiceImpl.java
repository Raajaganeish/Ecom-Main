package com.Angular.Service;

import com.Angular.CartEntities.ShoppingCart;
import com.Angular.JPA.RoleJPA;
import com.Angular.JPA.UserJPA;
import com.Angular.UserDetails.Role;
import com.Angular.UserDetails.User;
import com.Angular.UserDetails.UserPayment;
import com.Angular.UserDetails.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJPA user_jpa;

    @Autowired
    private RoleJPA role_jpa;

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user_obj = user_jpa.findByUsername(username);

        if(user_obj==null)
        {
            log.warn("User {} Not Forund",username);
            throw new UsernameNotFoundException("Username : { " + username+ "} Not Found");
        }

        return user_obj;
    }

    @Override
    public User createUser(User usr_obj, Set<UserRole> ur_role) {

        User local_user = this.user_jpa.findByUsername(usr_obj.getUsername());

        if(local_user!=null)
        {
            log.warn("userName Already exists!!!!!!");
            throw new UsernameNotFoundException("User name {"+usr_obj.getUsername()+"} already exists");
        }
        else{

            for(UserRole ur:ur_role)
            {
            	if(role_jpa.findByName(ur.getRole().getName())==null)
            	{
            		role_jpa.save(ur.getRole());
            	}
                
            }

            usr_obj.getUserRoles().addAll(ur_role);
            
            usr_obj.setUserPayments(new ArrayList<UserPayment>());
            
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(usr_obj);
            usr_obj.setShoppingCart(shoppingCart);

            local_user = user_jpa.save(usr_obj);

        }
        return local_user;
    }

    @Override
    public User findByEmail(String email) {
        User user1 = user_jpa.findByEmail(email);
        return user1;
    }

    @Override
    public void setNewPassword(User user,String password) {


        String newPass = passwordEncoder().encode(password);
        user.setPassword(newPass);
        System.out.println(password+"\n"+newPass);
        user_jpa.save(user);
    }


    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

	@Override
	public Role findRoleByName(String name) {
		
		Role tempRole = role_jpa.findByName(name);
		return tempRole;
	}

	@Override
	public User findByUsername(String username) {		
		
		return user_jpa.findByUsername(username);
	}

	@Override
	public User SetPasswordAlone(User user,String password) {
		
		user.setPassword(passwordEncoder().encode(password));
		return user;
	}

	@Override
	public Optional<User> findById(Integer id) {
		// TODO Auto-generated method stub
		return user_jpa.findById(id);
	}

	@Override
	public void save(User ur) {
		// TODO Auto-generated method stub
		user_jpa.save(ur);
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return user_jpa.findAll();
	}
}
