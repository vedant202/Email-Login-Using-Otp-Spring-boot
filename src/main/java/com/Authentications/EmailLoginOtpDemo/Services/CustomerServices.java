package com.Authentications.EmailLoginOtpDemo.Services;

import java.util.List;

import com.Authentications.EmailLoginOtpDemo.Models.Customer;

public interface CustomerServices {
	List<Customer> getAllCustomers();
	Customer createCustomer(Customer customer);
	Customer getCustomer(String id) throws Exception;
	Customer getCustomerByEmail(String email);
}
