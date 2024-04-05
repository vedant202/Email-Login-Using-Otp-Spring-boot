package com.Authentications.EmailLoginOtpDemo.DTOs;

public class Otp {
	private String otp;

	public Otp() {}

	public Otp(String otp) {
		super();
		this.otp = otp;
	}


	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}


	@Override
	public String toString() {
		return "Otp [otp=" + otp + "]";
	}


}
