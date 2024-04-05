package com.Authentications.EmailLoginOtpDemo.Models;



import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document("Customers")
public class Customer {

	@Id
	private String id;

	private String first_name;

	private String last_name;

	@NonNull
	private String email;

	private String password;

	private String one_time_password;
	private Date otp_requested_time;
	
	
	private String role="ROLE_USER";

	private static final long OTP_VALID_DURATION = 5*60*1000;

	public Customer() {
	}
	
	public Customer(String id, String first_name, String last_name, String email, String password,
			String one_time_password, Date otp_requested_time) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.one_time_password = one_time_password;
		this.otp_requested_time = otp_requested_time;
	}
	
	public Customer(String id, String first_name, String last_name, String email, String password,
			String one_time_password, Date otp_requested_time, String role) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.one_time_password = one_time_password;
		this.otp_requested_time = otp_requested_time;
		this.role = role;
	}

	public Date getOtp_requested_time() {
		return otp_requested_time;
	}
	public void setOtp_requested_time(Date otp_requested_time) {
		this.otp_requested_time = otp_requested_time;
	}
	



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getOtpValidDuration() {
		return OTP_VALID_DURATION;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOne_time_password() {
		return one_time_password;
	}
	public void setOne_time_password(String one_time_password) {
		this.one_time_password = one_time_password;
	}

	public boolean isOtpRequired() {
		if(this.getOne_time_password()==null) {
			return false;
		}

		long curentTimeMillis = System.currentTimeMillis();

		System.out.println("Otp requested Time is :- "+this.otp_requested_time.getTime());
		System.out.println("this.otp_requested_time.getTime()+OTP_VALID_DURATION :- "+(this.otp_requested_time.getTime()+OTP_VALID_DURATION));
		System.out.println("Current time in millisecond :- "+curentTimeMillis);
		
		if(this.otp_requested_time.getTime()+OTP_VALID_DURATION>curentTimeMillis) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", one_time_password=" + one_time_password + ", otp_requested_time="
				+ otp_requested_time + ", role=" + role + "]";
	}



}
