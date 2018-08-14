package com.deniz.kutuphane.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deniz.kutuphane.entities.Authorities;
import com.deniz.kutuphane.entities.Users;
import com.deniz.kutuphane.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {

		return "form/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterPage(Model model) {

		model.addAttribute("user", new Users());
		return "/form/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegisterPage(@ModelAttribute Users user) {

		Authorities authorities = new Authorities();
		authorities.setUsername(user.getUsername());
		authorities.setAuthority("ROLE_USER");

		Collection<Authorities> list = new ArrayList<Authorities>();
		list.add(authorities);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setAuthorities(list);
		userRepository.save(user);

		return "pages/home";
	}

	@RequestMapping(value = { "", "/home" }, method = RequestMethod.GET)
	public String getHomePage() {
		return "pages/home";
	}

}
