package com.in28minutes.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	// let springmvc to set an service
	@Autowired
	private LoginService service;

	// ResponseBody--return directly instead of looking for url
	// @ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	// pass parameter in
	public String handleUserLogin(ModelMap model, @RequestParam String name,
			@RequestParam String password) {
		if (!service.validateUser(name, password)) {
			// put error message
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		model.put("name", name);
		return "welcome";
	}
}
