package com.apap.tutorial8.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		// TODO Auto-generated method stub
		String password = encrypt(user.getPassword());
		user.setPassword(password);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserRoleModel getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userDb.findByUsername(username);
	}

	@Override
	public boolean isValidPassword(String passwordDatabase, String username) {
		// TODO Auto-generated method stub
		UserRoleModel user = userDb.findByUsername(username);
		BCryptPasswordEncoder bCryptPassword = new BCryptPasswordEncoder();
		
		if(bCryptPassword.matches(passwordDatabase, user.getPassword())) {
			return true;
		}
		return false;
		
	}

	@Override
	public void updatePassword(String passwordBaru, String username) {
		// TODO Auto-generated method stub
		UserRoleModel user = userDb.findByUsername(username);
		String encodePassword = this.encrypt(passwordBaru);
		user.setPassword(encodePassword);
		userDb.save(user);
	}

	@Override
	public boolean validStandardPassword(String passwordBaru) {
		// TODO Auto-generated method stub
		boolean validPassword = passwordBaru.matches("(?=.*[0-9])(?=.*[a-zA-Z]).{8,}");
		return validPassword;
	}
	
}
