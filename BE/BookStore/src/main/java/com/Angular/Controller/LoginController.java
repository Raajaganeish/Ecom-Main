package com.Angular.Controller;


import com.Angular.Helper.GenerateSecurePassword;
import com.Angular.Helper.SendMail;
import com.Angular.Service.UserService;
import com.Angular.UserDetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    GenerateSecurePassword pass_obj = new GenerateSecurePassword();
    SendMail mail_obj = new SendMail();


    @Autowired
    public UserService userService;

    private String sid="";

    @RequestMapping("/token")
    public Map<String,String>token(HttpSession session, HttpServletRequest request)
    {
    	
        String host = request.getRemoteHost();
        int port = request.getRemotePort();
        System.out.println("Details ===>\n Host \t"+host+"\n Port \t"+port+"\n");
        sid = session.getId();
        System.out.println("Id   "+sid);   	
    	
        return Collections.singletonMap("token",session.getId());
    }


    @RequestMapping("/checkSession")
    public ResponseEntity checkSession(HttpServletRequest request) {
//        String id = request.getHeader("x-auth-token");
//        System.out.println("From Request : Id \t"+id);
//        if(sid.equals(id))
//        {
//            System.out.println("Equal");
//            return new ResponseEntity("Session Active!", HttpStatus.OK);
//        }
//
//        return new ResponseEntity("Session Not Active!", HttpStatus.NOT_FOUND);
    	Map<String, String> map = new HashMap<String, String>();
    	Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        
        map.forEach((k,v)->{
//        	System.out.println(k.toString()+"\t"+v.toString());
        });
    	
        
    	String name;
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	System.out.println("Check Session");
    	System.out.println(auth.getName());
        if (auth == null || auth.getPrincipal()=="anonymousUser") {            
            return new ResponseEntity("Session Not Active!", HttpStatus.NOT_FOUND);            
        }
//    	HttpSession session = request.getSession();
//    	String snameString = (String)session.getAttribute("name");
//    	
//    	System.out.println(snameString);
//    	
//    	if(snameString==null)
//    	{
//    		return new ResponseEntity("Session Not Active!", HttpStatus.NOT_FOUND);
//    	}
    	
        
        return new ResponseEntity("Session Active!", HttpStatus.OK);
    }


    @PostMapping("/logout_user")
    public ResponseEntity logout(HttpServletRequest request)
    {
//        System.out.println("Logging out");
//        sid="sdasdasd";
//        SecurityContextHolder.clearContext();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("Before Logout");
    	System.out.println(auth.getName());
//        
//        HttpSession session= request.getSession(false);
//        SecurityContextHolder.clearContext();
//             session= request.getSession(false);
//            if(session != null) {
//                session.invalidate();
//            }
//            if(request.getCookies()!=null)
//            {
//            	for(Cookie cookie : request.getCookies()) {
//                    cookie.setMaxAge(0);
//                }
//            }
//            
    	SecurityContextHolder.clearContext();
    	System.out.println(Session.class);
            
            
        return new ResponseEntity("Session Closed",HttpStatus.OK);
    }


    @PostMapping("/forgot_Password")
    public ResponseEntity newPassword(@RequestBody HashMap<String,String> mapper)
    {
        String email = mapper.get("email");
        System.out.println("Email"+email);
        User user = userService.findByEmail(email);
        if(user==null)
        {
            return new ResponseEntity("Email not found",HttpStatus.NOT_FOUND);
        }
        else
        {
            String password = pass_obj.generatePassword(6);
            System.out.println("password:" + password);
            userService.setNewPassword(user,password);
            SendMail.Send_Password(user.getUsername(),email,password);
            return new ResponseEntity("Email Sent",HttpStatus.OK);
        }
    }






}
