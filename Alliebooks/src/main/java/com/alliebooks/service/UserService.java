package com.alliebooks.service;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alliebooks.dto.UserRegistrationDto;
import com.alliebooks.entities.User;
import com.alliebooks.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger log = LogManager.getLogger();

	@Transactional
	public User registerNewUserAccount(UserRegistrationDto userDto) throws Exception {

		if (usernameExists(userDto.getUserName())) {
			throw new Exception("There is an account with username: " + userDto.getUserName());
		}

		User user = new User();
		user.setUsername(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole("USER");// TODO this is hardcoded to user, figure out how to do admin accounts

		try {
			user = userRepository.save(user);
		} catch (Exception e) {
			log.error("Error while creating account", e);
			throw new Exception("Error while creating account");
		}

		return user;
	}

	// TODO this method is called 100000 times just to get the current user id.
	// Store that in the security context to save on db calls.
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in usernameExists
		return userRepository.findByUsername(name);
	}

	public User getUserByName(String name) {
		return userRepository.findByUsername(name);
	}

	public User findById(UUID id) {
		return userRepository.findById(id).get();
	}
	
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}
}
