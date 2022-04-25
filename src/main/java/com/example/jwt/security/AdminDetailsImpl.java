package com.example.jwt.security;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.jwt.models.AdminDetails;


public class AdminDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	static final Logger log = LoggerFactory.getLogger(AdminDetailsImpl.class);
	
	private long adminId;
	
	private String firstName;

	private String lastName;

	private String emailId;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	public AdminDetailsImpl(long adminId, String firstName, String lastName, String emailId, 
			String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.authorities = authorities;
	}
	
	public AdminDetailsImpl() {
		// TODO Auto-generated constructor stub
	}

	

	public static AdminDetailsImpl build(AdminDetails admin) {	
		log.info("****Inside AdminDetailsImpl build method***");	
		
	//	List<AdminRoles> adminData=adminRolesRepository.findRoles();
		
		
		List<GrantedAuthority> authorities=new LinkedList<>();
		log.info(" After authoritiesList-------");
		
		
		
		admin.getAdminRole();
		log.info("Admin role in AdminDetailsImpl----"+admin.getAdminRole());
		if(admin.getAdminRole()!=null) {
			log.info("Inside if AdminRole is not null"+admin.getAdminRole());
			authorities.add(new SimpleGrantedAuthority(admin.getAdminRole()));
		}
		
		/*
		 if(ERole.ADMIN_ROLE==admin.getAdminRole()){
			log.info("Inside if Admin role is not null-"+admin.getAdminRole());
			authorities.add(new SimpleGrantedAuthority("ADMIN_ROLE"));
		}else if(ERole.MASTERADMIN_ROLE==admin.getAdminRole()) {
			log.info("Inside if UserRole is not null"+admin.getAdminRole());
			authorities.add(new SimpleGrantedAuthority("MASTERADMIN_ROLE"));
		}
		 
		 */
		 
		
		 
		log.info("Authorities-----"+authorities);
		log.info(" Before build of AdminDetailsImpl");
		return new AdminDetailsImpl(admin.getAdminId(), admin.getFirstName(), admin.getLastName(),admin.getEmailId(),admin.getPassword(),
				authorities);
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLog() {
		return log;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AdminDetailsImpl admin = (AdminDetailsImpl) o;
		return Objects.equals(adminId, admin.adminId);
	}

	@Override
	public String toString() {
		return "AdminDetailsImpl [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", password=" + password + ", authorities="
				+ authorities + "]";
	}


	
	
	
	

}