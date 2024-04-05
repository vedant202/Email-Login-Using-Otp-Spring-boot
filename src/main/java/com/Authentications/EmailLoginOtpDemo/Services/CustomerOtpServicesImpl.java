package com.Authentications.EmailLoginOtpDemo.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Authentications.EmailLoginOtpDemo.Models.Customer;
import com.Authentications.EmailLoginOtpDemo.Repositories.CustomerRepositories;

import net.bytebuddy.utility.RandomString;


@Service
public class CustomerOtpServicesImpl {

	//For sending mail
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	CustomerRepositories customerRepositories;


	public boolean validateOtp(String email,String otp) {

		Customer customer = customerRepositories.findByEmail(email);
		System.out.println("validateOtp Customer email is :- "+email);
		System.out.println("validateOtp otp is :- "+otp);

		System.out.println("validateOtp Customer otp is :- "+customer.getOne_time_password());
		System.out.println("validateOtp Customer otp is :- "+customer.getOne_time_password().equalsIgnoreCase(otp));
		System.out.println("Checking isOtpRequired :- "+customer.isOtpRequired());
		if(otp.isBlank() || otp.isEmpty() || customer.isOtpRequired() || !customer.getOne_time_password().equalsIgnoreCase(otp)) {
			return false;
		}

		return true;
	}

	public String generateOtp(Customer customer) {
		try {
			String otp = RandomString.make(6);
			customer.setOne_time_password(otp);
			System.out.println("Ne date :- "+new Date());
			customer.setOtp_requested_time(new Date());
			customerRepositories.save(customer);
			sendOtp(customer,otp);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public void clearOtp(String email) {
		System.out.println("Clearing otp of client "+email);
		Customer customer = customerRepositories.findByEmail(email);
		customer.setOne_time_password(null);
		customer.setOtp_requested_time(null);
		customerRepositories.save(customer);
	}

	private void sendOtp(Customer customer, String otp) {
		// TODO Auto-generated method stub
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("vedantdhenge9@gmail.com");
		mailMessage.setTo(customer.getEmail());
		mailMessage.setText("Your otp is "+otp+" is only valid for 5 minutes");
		mailMessage.setSubject("EmailLoginOtpDemo Authentication");

		mailSender.send(mailMessage);

	}
}
