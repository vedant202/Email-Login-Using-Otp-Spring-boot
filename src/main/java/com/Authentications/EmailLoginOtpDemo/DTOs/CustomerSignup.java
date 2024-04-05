package com.Authentications.EmailLoginOtpDemo.DTOs;

public class CustomerSignup {
	private String fName;
	private String lName;
	private String email;
	private String password;
	private String cpassword;

	public CustomerSignup() {
	}

	public CustomerSignup(String fName, String lName, String email, String password, String cpassword) {
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.cpassword = cpassword;
	}




	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
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
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	@Override
	public String toString() {
		return "CustomerSignup [fName=" + fName + ", lName=" + lName + ", email=" + email + ", password=" + password
				+ ", cpassword=" + cpassword + "]";
	}

}
