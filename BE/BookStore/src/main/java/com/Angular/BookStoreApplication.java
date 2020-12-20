package com.Angular;

import com.Angular.Service.UserService;
import com.Angular.UserDetails.Role;
import com.Angular.UserDetails.User;
import com.Angular.UserDetails.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {


//		User usr_obj = new User("raaja96","raja","ganeish","raaja.ganeish@gmail.com","7418546046");
//		usr_obj.setPassword(passwordEncoder().encode("virus13b"));
//		Set<UserRole> ur_role = new HashSet<>();
//		Role role_obj = userService.findRoleByName("ROLE_ADMIN");
//		ur_role.add(new UserRole(usr_obj,role_obj));
//
//		userService.createUser(usr_obj,ur_role);
//
//		ur_role.clear();
//
//
//		User user1 = new User("test","Test","Test","test@gmail.com","99999999");
//		user1.setPassword(passwordEncoder().encode("virus13b"));
//		Set<UserRole> userRoles = new HashSet<>();
//		Role role1 = userService.findRoleByName("ROLE_USER");
//		userRoles.add(new UserRole(user1, role1));
//
//		userService.createUser(user1, userRoles);
//
//		userRoles.clear();

	}

	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
