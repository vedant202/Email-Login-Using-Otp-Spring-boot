package com.Authentications.EmailLoginOtpDemo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private customSuccessHandler customSuccessHandler;

	@Autowired
	private UserDetails userDetailsService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetails();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(passwordEncoder());

		return auth;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests().requestMatchers("/js/**","/login","/signup").permitAll()
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/").hasAnyRole("USER","ADMIN")
		.anyRequest().authenticated()
		
		.and()
		.formLogin().loginPage("/login").usernameParameter("email")
		.loginProcessingUrl("/login").permitAll().successHandler(customSuccessHandler)
				;

		return http.build();
	}
}
