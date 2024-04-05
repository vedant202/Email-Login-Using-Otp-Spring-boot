package com.Authentications.EmailLoginOtpDemo.Services;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Authentications.EmailLoginOtpDemo.Models.Customer;
import com.Authentications.EmailLoginOtpDemo.Repositories.CustomerRepositories;

@Service
public class CustomerServiceImpl implements CustomerServices {

	@Autowired
	CustomerRepositories repo;

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}



	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer c = repo.save(customer);
		return c;
	}

	@Override
	public Customer getCustomer(String id) throws Exception {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(()->new Exception("user with "+id+" not found"));
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}

}
