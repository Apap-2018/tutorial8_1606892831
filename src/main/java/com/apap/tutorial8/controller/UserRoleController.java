package com.apap.tutorial8.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user, Model model) {
		if(userRoleService.validStandardPassword(user.getPassword())) {
			userRoleService.addUser(user);
		}
		else {
			model.addAttribute("error", "Invalid Format Password");
		}
		
		return "home";
	}
	
	@RequestMapping(value="/updatePassword", method = RequestMethod.POST)
	private String updatePasswordSubmit(@RequestParam(value="passwordLama") String passwordLama, 
			@RequestParam(value="passwordBaru") String passwordBaru,
			@RequestParam(value="passwordBaru") String konfirmasiPasswordBaru,
			HttpServletRequest request, Model model) {
		
		if(passwordBaru.equals(konfirmasiPasswordBaru)){
			if(userRoleService.isValidPassword(passwordLama, request.getRemoteUser())) {
				if(userRoleService.validStandardPassword(passwordBaru)) {
					userRoleService.updatePassword(passwordBaru, request.getRemoteUser());
				}
				else {
					model.addAttribute("error", "Invalid Format Password ");
				}
			}
			else {
				model.addAttribute("error","Invalid Old Password");
			}
		}
		else {
			model.addAttribute("error","Invalid Confirm Password");
		}
	
		return "home";
	}
}
