/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.UserAccountService;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController extends AbstractController {

	@Autowired
	private UserAccountService	userAccountService;


	// Constructors -----------------------------------------------------------

	public UserAccountController() {
		super();
	}
	// Register ---------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		UserAccount user;

		user = this.userAccountService.create();

		result = this.createEditModelAndView(user);

		return result;
	}

	protected ModelAndView createEditModelAndView(final UserAccount user) {
		return this.createEditModelAndView(user, null);
	}

	protected ModelAndView createEditModelAndView(final UserAccount user, final String msg) {
		ModelAndView result;
		result = new ModelAndView("userAccount/create");
		result.addObject("user", user);
		result.addObject("message", msg);
		return result;
	}
}
