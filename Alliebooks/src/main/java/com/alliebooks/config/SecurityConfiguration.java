package com.alliebooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@EnableWebSecurity
public class SecurityConfiguration implements WebMvcConfigurer{
	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("allie").password("hello").roles("USER").build());
		return manager;
	}

	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic();
	}

}
