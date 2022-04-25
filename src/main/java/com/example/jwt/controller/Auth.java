package com.example.jwt.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.models.AdminDetails;
import com.example.jwt.response.AdminDetailsResponse;
import com.example.jwt.response.CustomResponseForLogin;
import com.example.jwt.response.CustomResponseForNoUser;
import com.example.jwt.security.AdminDetailsImpl;
import com.example.jwt.security.JwtUtils;
import com.example.jwt.services.AuthService;

@RestController
public class Auth {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtutils;
	
	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}
	
	@PostMapping("/createAdmin")
	public ResponseEntity<Object> createAdmin(@RequestBody AdminDetails adminDetails)
	{
		AdminDetails fetchAdmin=authService.fetchAdminByEmail(adminDetails.getEmailId());
		
		if(fetchAdmin == null)
		{
			AdminDetails admin=authService.createAdmin(adminDetails);
			AdminDetailsResponse response=new AdminDetailsResponse(new Date(),"Admin Created Successfully","200",admin);
			return new ResponseEntity<Object>(response,HttpStatus.OK);
			
		}
		
		else
		{
			CustomResponseForNoUser response=new CustomResponseForNoUser(new Date(),"Email already used","409");
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		
		
	}
	
	@PostMapping("/adminLogin")
	public ResponseEntity<Object> adminLogin(@RequestBody AdminDetails adminDetails)
	{
		AdminDetails fetchAdmin=authService.fetchAdminByEmail(adminDetails.getEmailId());
		
		if( fetchAdmin != null)
		{
			if(encoder.matches(adminDetails.getPassword(), fetchAdmin.getPassword())) {
				Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDetails.getEmailId(),adminDetails.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt=jwtutils.generateJwtToken(authentication);
				
				AdminDetailsImpl details=(AdminDetailsImpl)authentication.getPrincipal();
				
				if( details != null)
				{
					CustomResponseForLogin response=new CustomResponseForLogin(new Date(),"Login Successful","200",jwt,adminDetails.getFirstName(),adminDetails.getLastName(),adminDetails.getPassword());
					return new ResponseEntity<Object>(response,HttpStatus.OK);
				}
				else
				{
					CustomResponseForNoUser response=new CustomResponseForNoUser(new Date(),"Error in Authentication","409");
					return new ResponseEntity<Object>(response,HttpStatus.OK);
				}
			}
			else
			{
				CustomResponseForNoUser response=new CustomResponseForNoUser(new Date(),"Invalid Password","409");
				return new ResponseEntity<Object>(response,HttpStatus.OK);
			}
		}
		else
		{
			CustomResponseForNoUser response=new CustomResponseForNoUser(new Date(),"No Such Email","409");
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}
		
		
		
	}
	
	@GetMapping("/alladmins")
	public List<AdminDetails> getAllAdmins(){
		List<AdminDetails> alladmins=authService.alladmins();
		return alladmins;
		
	}

}
