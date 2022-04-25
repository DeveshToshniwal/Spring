package com.example.jwt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwt.models.AdminDetails;
import com.example.jwt.repository.AuthRepository;
import com.example.jwt.security.AdminDetailsImpl;


@Service
public class AdminDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AuthRepository authRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		
		AdminDetails admin=authRepository.fetchAdminByEmail(email);
		if(admin == null)
		{
			throw new UsernameNotFoundException("Admin not found for this email");
		}
		return AdminDetailsImpl.build(admin);
		
	}

}
