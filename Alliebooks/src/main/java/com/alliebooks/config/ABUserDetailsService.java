package com.alliebooks.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alliebooks.entities.User;
import com.alliebooks.repo.UserRepository;

@Service
public class ABUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired private UserRepository userRepository;
	
	private static final Logger log = LogManager.getLogger();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		log.info("User " + username + " logging in");
		return user;
	}

}
