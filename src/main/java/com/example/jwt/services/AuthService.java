package com.example.jwt.services;

import java.util.List;

import com.example.jwt.models.AdminDetails;

public interface AuthService {
	
	AdminDetails createAdmin(AdminDetails adminDetails);
	
	AdminDetails fetchAdminByEmail(String email);

	List<AdminDetails> alladmins();

}
