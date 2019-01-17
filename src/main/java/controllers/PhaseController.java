/*
 * ComplaintController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PhaseService;
import domain.Phase;

@Controller
@RequestMapping("/phases")
public class PhaseController extends AbstractController {

	@Autowired
	private PhaseService	phaseService;


	// Constructors -----------------------------------------------------------

	public PhaseController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Phase phase;

		phase = this.phaseService.createPhase();

		result = this.createEditModelAndView(phase);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Phase> phase;

		phase = this.phaseService.findAll();

		res = new ModelAndView("phase/list");
		res.addObject("phases", phase);
		res.addObject("requestURI", "phase/list.do");
		return res;
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam final int id) {
		ModelAndView result;
		Phase phase;

		phase = this.phaseService.findOne(id);
		Assert.notNull(phase);
		result = this.createEditModelAndView(phase);

		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Phase phase, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(phase);
		else
			try {
				this.phaseService.save(phase);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(phase, "phase.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Phase phase, final BindingResult binding) {
		ModelAndView result;

		try {
			this.phaseService.delete(phase);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(phase, "phase.commit.error");

		}
		return result;
	}
	protected ModelAndView createEditModelAndView(final Phase phase) {
		ModelAndView result;

		result = this.createEditModelAndView(phase, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(final Phase phase, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("phase/list");
		result.addObject("Phase", phase);
		result.addObject("message", messageCode);

		return result;
	}

}
