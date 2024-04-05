package com.Authentications.EmailLoginOtpDemo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.Authentications.EmailLoginOtpDemo.Models.Customer;
import com.Authentications.EmailLoginOtpDemo.Repositories.CustomerRepositories;

@Component
public class UserDetails implements UserDetailsService {

	@Autowired
	private CustomerRepositories customerRepositories;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("User Details Service email "+email);
		Customer customer=customerRepositories.findByEmail(email);
		System.out.println("User Details Service "+ customer);


		return new CustomUsers(customer);
	}
	


}
