package com.Authentications.EmailLoginOtpDemo.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Authentications.EmailLoginOtpDemo.Models.Customer;

@Repository
public interface CustomerRepositories extends MongoRepository<Customer, String> {
	Customer findByEmail(String email);
}
