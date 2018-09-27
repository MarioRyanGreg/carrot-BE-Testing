package com.mitrais.carrot.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mitrais.carrot.config.jwt.JwtTokenProvider;
import com.mitrais.carrot.config.jwt.UserPrincipal;

public class UserPrincipalData {

	public UserPrincipal getPrincipalData() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		UserPrincipal userPrincipal = new UserPrincipal(1L, "cakpep", "cakpep", "", "cakpep", authorities);

		return userPrincipal;
	}

	public String getToken() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		UserPrincipal userPrincipal = new UserPrincipal(1L, "cakpep", "cakpep", "", "cakpep", authorities);
		JwtTokenProvider jwt = new JwtTokenProvider();
		String token =  jwt.generateToken(userPrincipal); 

		return token;
	}
}
