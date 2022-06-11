package com.lowes.empapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.lowes.empapp.dao.UserDaoImpl;
import com.lowes.empapp.exception.LoginException;
import com.lowes.empapp.model.User;
import com.lowes.empapp.service.EmployeeServiceJdbcImpl;

/**
 * Servlet implementation class RegistrationController
 */
@Controller
public class RegistrationController {
	      
	@Autowired
	UserDaoImpl userDao;
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)	
	public ModelAndView showRegister(HttpServletRequest req, HttpServletResponse resp) {

		return new ModelAndView("registerUser", "command", new User());
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@ExceptionHandler(LoginException.class)
	public ModelAndView addUser(Model model, @ModelAttribute User user) {
			
		System.out.println(user);
		System.out.println(userDao);
		userDao.registerUser(user);		
		return new ModelAndView("redirect:/login");
		
	}
	
}
