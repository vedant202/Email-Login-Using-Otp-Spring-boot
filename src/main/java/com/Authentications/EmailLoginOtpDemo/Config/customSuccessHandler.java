package com.Authentications.EmailLoginOtpDemo.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Authentications.EmailLoginOtpDemo.Models.Customer;
import com.Authentications.EmailLoginOtpDemo.Services.CustomerOtpServicesImpl;
import com.Authentications.EmailLoginOtpDemo.Services.CustomerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class customSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private CustomerOtpServicesImpl otpServicesImpl;

	@Autowired
	private CustomerServiceImpl serviceImpl;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		UserDetails userDetails=(UserDetails)authentication.getPrincipal();
		System.out.println("Custom Succes Handler "+userDetails);

		String email = userDetails.getUsername();
		
		System.out.println("Custom Succes Handler email :- "+email);
		
		Customer customer=serviceImpl.getCustomerByEmail(email);
		
		System.out.println("Custom Succes Handler customer :- "+customer);
		
		String output=otpServicesImpl.generateOtp(customer);
		System.out.println("Custome success handler Output :- "+output);
		String redirectUrl = "";
		if(output.equalsIgnoreCase("success")) {
			redirectUrl="/login/twoStepVerfication";
		}else {
			redirectUrl="/login";
		}



		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);

	}

}
