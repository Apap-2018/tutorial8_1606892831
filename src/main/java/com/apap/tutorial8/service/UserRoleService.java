package com.apap.tutorial8.service;

import com.apap.tutorial8.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	void updatePassword(String passwordBaru, String username);
	public String encrypt(String password);
	UserRoleModel getUserByUsername(String username);
	public boolean isValidPassword(String passwordDatabase, String username);
	public boolean validStandardPassword(String passwordBaru);
}
