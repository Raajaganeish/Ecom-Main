package com.Angular.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Angular.Helper.GenerateSecurePassword;
import com.Angular.Helper.SendMail;
import com.Angular.Service.UserService;
import com.Angular.UserDetails.Role;
import com.Angular.UserDetails.User;
import com.Angular.UserDetails.UserRole;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService user_service;
	
	
	@PostMapping("/new_user")
	public ResponseEntity<String> register(@RequestBody HashMap<String, String> hmap)
	{
		String usernameString = hmap.get("username");
		String emailString = hmap.get("email");
		
		System.out.println("Username"+usernameString+"EMail"+emailString);
		
		if(user_service.findByUsername(usernameString)!=null)
		{
			return new ResponseEntity<String>("UserName Already exists", HttpStatus.BAD_REQUEST);
		}
		if(user_service.findByEmail(emailString)!=null)
		{
			return new ResponseEntity<String>("Email Already exists", HttpStatus.BAD_REQUEST);
		}
		
		User newUser = new User();
		newUser.setUsername(usernameString);
		newUser.setEmail(emailString);
		String passwordSecureString = GenerateSecurePassword.generatePassword(6);
		newUser = user_service.SetPasswordAlone(newUser, passwordSecureString);
		Role rlRole = user_service.findRoleByName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(newUser, rlRole));
		user_service.createUser(newUser, userRoles);
		
		userRoles.clear();
		
		SendMail.Send_Password(newUser.getUsername(), newUser.getEmail(), passwordSecureString);
		
		
		return new ResponseEntity("Registered Successfully", HttpStatus.OK);
	}

}
