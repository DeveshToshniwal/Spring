package com.example.jwt.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwt.serviceimpl.AdminDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Autowired
	AdminDetailsServiceImpl adminDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	/*
	 * @Autowired AuthTokenFilter filter;
	 */
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		log.info("Inside AuthTokenFilter of WebSecurityConfig ");
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		log.info("******** Inside  configure method of WebSecurityConfig Class****** ");
		authenticationManagerBuilder.userDetailsService(adminDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		log.info("Inside authenticationManagerBean of WebSecurityConfig ");
		return super.authenticationManagerBean();
	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); }
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("Inside PasswordEncoder of WebSecurityConfig ");
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("Inside configure method of WebSecurityConfig ");
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/createAdmin/**").permitAll()
				.antMatchers("/adminLogin/**").permitAll()
				.antMatchers("/alladmins").permitAll()
				.anyRequest().authenticated();
		log.info("Before AddFilter of WebSecurityConfig");
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
		

}