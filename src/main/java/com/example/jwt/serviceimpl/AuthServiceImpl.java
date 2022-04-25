package com.example.jwt.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt.models.AdminDetails;
import com.example.jwt.repository.AuthRepository;
import com.example.jwt.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public AdminDetails createAdmin(AdminDetails adminDetails) {
		// TODO Auto-generated method stub
		adminDetails.setPassword(encoder.encode(adminDetails.getPassword()));
		return authRepository.save(adminDetails);
	}

	@Override
	public AdminDetails fetchAdminByEmail(String email) {
		// TODO Auto-generated method stub
		return authRepository.fetchAdminByEmail(email);
	}

	@Override
	public List<AdminDetails> alladmins() {
		// TODO Auto-generated method stub
		return authRepository.fetchAllAdmins();
	}

}
