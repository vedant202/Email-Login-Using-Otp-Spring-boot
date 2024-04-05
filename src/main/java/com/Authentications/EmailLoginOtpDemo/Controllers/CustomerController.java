package com.Authentications.EmailLoginOtpDemo.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Authentications.EmailLoginOtpDemo.DTOs.CustomerSignup;
import com.Authentications.EmailLoginOtpDemo.DTOs.Otp;
import com.Authentications.EmailLoginOtpDemo.Models.Customer;
import com.Authentications.EmailLoginOtpDemo.Services.CustomerOtpServicesImpl;
import com.Authentications.EmailLoginOtpDemo.Services.CustomerServiceImpl;

@Controller
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	private CustomerOtpServicesImpl otpServicesImpl;

	@GetMapping("/")
	public String index(Principal p,Model m) {
		System.out.println(p.getName());
		m.addAttribute("name", p.getName());
		otpServicesImpl.clearOtp(p.getName());
		return "Index";
	}

	@GetMapping("/signup")
	public String signup() {
		System.out.println("dignup");
		return "Signup";
	}

	@GetMapping("/login")
	public String login() {
		System.out.println("login");
		return "Login";
	}

	@GetMapping("/login/twoStepVerfication")
	public String loginTwoStep() {
		System.out.println("TwoStepVerification");
		return "TwoStepVerification";
	}

	@PostMapping("/login/twoStepVerfication")
	public String validateOtp(Principal p,@ModelAttribute Otp otp) {
		System.out.println("Post Mapping to twoStepVerfication");
		System.out.println("cl");
		System.out.println("Client otp is :- "+ otp);
		if(otp.getOtp()==null || otp.getOtp().isEmpty()) {
			otpServicesImpl.clearOtp(p.getName());
			return "redirect:/logout";
		}

		boolean validate=otpServicesImpl.validateOtp(p.getName(), otp.getOtp());
		System.out.println("client otp validation :- "+validate);
		if(!validate) {
			otpServicesImpl.clearOtp(p.getName());
			return "redirect:/logout";
		}
		otpServicesImpl.clearOtp(p.getName());
		return "redirect:/";
	}

	@PostMapping("/signup")
	public void addCustomer(@RequestBody CustomerSignup customerSignup) {
		System.out.println(customerSignup);

		Customer customer = new Customer();
		customer.setFirst_name(customerSignup.getfName());
		customer.setLast_name(customerSignup.getlName());
		customer.setEmail(customerSignup.getEmail());
		customer.setPassword(new BCryptPasswordEncoder().encode(customerSignup.getPassword()));

		customerService.createCustomer(customer);


	}
}
