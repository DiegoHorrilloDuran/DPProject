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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	// edit ---------------------------------------------------------------		

	@RequestMapping("/edit")
	public ModelAndView edit() {
		ModelAndView result;

		result = new ModelAndView("profile/edit");

		return result;
	}

	// send ---------------------------------------------------------------		

	@RequestMapping("/send")
	public ModelAndView send() {
		ModelAndView result;

		result = new ModelAndView("profile/send");

		return result;
	}

}
