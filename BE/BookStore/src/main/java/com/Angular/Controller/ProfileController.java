package com.Angular.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Angular.Service.PaymentServiceInt;
import com.Angular.Service.UserService;
import com.Angular.UserDetails.User;
import com.Angular.UserDetails.UserBillAddress;
import com.Angular.UserDetails.UserPayment;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/Profile")
public class ProfileController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentServiceInt payment_service;
	
	@GetMapping("/currentUser")
	public User getUser(Principal principal)
	{
		String username = principal.getName();
		System.out.println("user:\t"+username);
		
		User userObj = new User();
		if(username!=null)
		{
			userObj = userService.findByUsername(username);
		}
		return userObj;
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody HashMap<String, String> hmap)
	{
		Optional<User> li_obj = userService.findById(Integer.parseInt(hmap.get("id")));
		User user_object = li_obj.get();
		user_object.setEmail(hmap.get("email"));
		user_object.setFirstname(hmap.get("firstname"));
		user_object.setLastname(hmap.get("lastname"));
//		user_object.setUsername(hmap.get("username"));
		user_object.setPhone(hmap.get("phone"));
		
		userService.save(user_object);
		return new ResponseEntity("Updated",HttpStatus.OK);
	}
	
	@GetMapping("/getPaymentInfo")
	public List<UserPayment> getAll(Principal Principal)
	{
		User user = new User();
		if(Principal!=null)
		{
			user = userService.findByUsername(Principal.getName());
			return user.getUserPayments();
		}
		else {
			return user.getUserPayments();
		}
		
	}
	
	@PostMapping("/paymentAdd")
	public ResponseEntity<String> updateCardDetails(@RequestBody UserPayment userPayment,Principal principal)
	{
		System.out.println(userPayment.toString());
		UserBillAddress userBillAddress = userPayment.getUserBillAddress();
		System.out.println(userBillAddress.toString());
		if(principal!=null)
		{
			String username = principal.getName();
			User user = userService.findByUsername(username);
			
			payment_service.updatePaymentDetails(userBillAddress,userPayment,user);
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/paymentRemove")
	public ResponseEntity<String> deletePaymentDetail(@RequestBody String id,Principal principal)
	{
		if(principal!=null)
		{
			payment_service.removeById(Integer.parseInt(id));
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/setDefault")
	public ResponseEntity<String> setDefault(@RequestBody List<UserPayment> paymentList,Principal principal)
	{
		if(principal!=null)
		{
			User user = userService.findByUsername(principal.getName());
			payment_service.saveAllinList(paymentList,user);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/getDefault")
	public UserPayment getDefault(Principal Principal)
	{
		User user = new User();
		if(Principal!=null)
		{
			user = userService.findByUsername(Principal.getName());
			List<UserPayment> list =  user.getUserPayments();
			for (UserPayment userPayment : list) {
				
				if(userPayment.isDefault())
				{
					return userPayment;
				}
			}
			return null;
		}
		else {
			return null;
		}
		
		
	}
}
