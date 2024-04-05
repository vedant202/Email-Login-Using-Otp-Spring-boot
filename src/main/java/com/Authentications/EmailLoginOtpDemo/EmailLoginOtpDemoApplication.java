package com.Authentications.EmailLoginOtpDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.Authentications.EmailLoginOtpDemo.Models.Customer;
import com.Authentications.EmailLoginOtpDemo.Repositories.CustomerRepositories;


//adding the user to mongo db at the time of application starts
@Component
class AdminUserDataLoader implements CommandLineRunner{
	@Autowired
	private CustomerRepositories repositories;
	
	@Override
	public void run(String... args) throws Exception {
		// Checking User exists
		
		Customer adminUser=repositories.findByEmail("vedantdhenge9@gmail.com");
		System.out.println("Checking admin user "+ adminUser);
		if(adminUser==null)
		{
			System.out.println("Adding admin user ");
			Customer c= new Customer();
			c.setEmail("vedantdhenge9@gmail.com");
			c.setFirst_name("Vedant");
			c.setLast_name("Dhenge");
			c.setPassword(new BCryptPasswordEncoder().encode("vedant"));
			c.setRole("ROLE_ADMIN");
			repositories.save(c);
		}
		
	}
	
}


@SpringBootApplication
public class EmailLoginOtpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailLoginOtpDemoApplication.class, args);
	}

}
