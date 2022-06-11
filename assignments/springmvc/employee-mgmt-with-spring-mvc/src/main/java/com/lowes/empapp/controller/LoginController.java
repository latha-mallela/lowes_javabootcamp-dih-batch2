package com.lowes.empapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.lowes.empapp.dao.UserDaoImpl;
import com.lowes.empapp.exception.LoginException;
import com.lowes.empapp.model.User;

/**
 * Handles requests for the application login page.
 */
@Controller
@SessionAttributes("userName")
public class LoginController {
	
	@Autowired
	UserDaoImpl userDao;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)	
	public ModelAndView login(Model model) {

		return new ModelAndView("login", "command", new User());
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ExceptionHandler(LoginException.class)
	public ModelAndView authenticate(@ModelAttribute User user, Model model, HttpSession session) {
		if(userDao.validateUser(user))
		{
			System.out.println("Authentication success");
			model.addAttribute("userName", user.getUserName());
			return new ModelAndView("redirect:/employee/list");
		}
		else
		{
			System.out.println("Authentication failed");
			throw new LoginException("Invalid User or Password");
		}
		
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, SessionStatus sessionStatus)
	{		
		sessionStatus.setComplete();
//		session.removeAttribute("userName");		
//		session.invalidate();
		return "redirect:/";
	}
}