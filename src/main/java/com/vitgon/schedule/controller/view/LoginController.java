package com.vitgon.schedule.controller.view;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.service.UserService;
import com.vitgon.schedule.util.MessageUtil;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("auth/login");
		return modelAndView;
	}
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject(user);
		modelAndView.setViewName("auth/register");
		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		Locale locale = (Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findByEmail(user.getEmail());
		
		if (userExists != null) {
			bindingResult
				.rejectValue("email", "error.user", MessageUtil.getAttribute("duplicateEmailError", locale));
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("auth/register");
		} else {
			userService.save(user);
			modelAndView.addObject("signUpSuccess", true);
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("auth/register");
		}
		
		return modelAndView;
	}
}
