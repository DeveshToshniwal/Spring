package com.example.jwt.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwt.serviceimpl.AdminDetailsServiceImpl;


@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AdminDetailsServiceImpl adminDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			logger.info("****** Inside doFilterInternal Method of AuthTokenFilter******");
			String jwt = parseJwt(request);
			
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				logger.info(jwt);
				logger.info("Inside If Enterd validateJwtToken is NotNull and Coreect  ");
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				logger.info("username---"+username);
				UserDetails userDetails = adminDetailsService.loadUserByUsername(username);
				logger.info("usersdetails object ---"+userDetails.toString());
				logger.info("After UserDetails Object in AuthTokenFilter");
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				logger.info("Get Authorities  in AuthTokenFilter-----"+userDetails.getAuthorities());
				logger.info("After UsernamePasswordAuthenticationToken Object in AuthTokenFilter");
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("After setDetails method in AuthTokenFilter");
				SecurityContextHolder.getContext().setAuthentication(authentication);
				logger.info("After setAuthentication method in AuthTokenFilter");
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		logger.info("before filterChain.doFilter line");
		filterChain.doFilter(request, response);
		logger.info("after filterChain.doFilter line");
	}

	private String parseJwt(HttpServletRequest request) {
		logger.info("****** Inside parseJwt method of AuthTokenFilter");
		String headerAuth = request.getHeader("Authorization");
		logger.info("headerAuth in AuthTokenFilter---"+headerAuth);
		
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			logger.info("Inside If of checking StringUtils and Bearer in AuthTokenFilter");
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}

}