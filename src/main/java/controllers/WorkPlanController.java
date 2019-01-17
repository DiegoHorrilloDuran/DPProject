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

import services.FixUpTaskService;
import services.WorkplanService;
import domain.FixUpTask;
import domain.Workplan;

@Controller
@RequestMapping("/workPlans")
public class WorkPlanController extends AbstractController {

	@Autowired
	private WorkplanService		workPlanService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	// Constructors -----------------------------------------------------------

	public WorkPlanController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int id) {
		ModelAndView result;
		Workplan workPlan;

		final FixUpTask fut = this.fixUpTaskService.findOne(id);
		workPlan = this.workPlanService.create(fut);

		result = this.createEditModelAndView(workPlan);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Workplan> workPlans;

		workPlans = this.workPlanService.findAll();

		res = new ModelAndView("workPlan/list");
		res.addObject("workPlans", workPlans);
		res.addObject("requestURI", "workPlan/list.do");
		return res;
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam final int id) {
		ModelAndView result;
		Workplan workPlan;

		workPlan = this.workPlanService.findOne(id);
		Assert.notNull(workPlan);
		result = this.createEditModelAndView(workPlan);

		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Workplan workPlan, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(workPlan);
		else
			try {
				this.workPlanService.save(workPlan);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(workPlan, "workPlan.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Workplan workPlan, final BindingResult binding) {
		ModelAndView result;

		try {
			this.workPlanService.delete(workPlan);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(workPlan, "workPlan.commit.error");

		}
		return result;
	}
	protected ModelAndView createEditModelAndView(final Workplan workPlan) {
		ModelAndView result;

		result = this.createEditModelAndView(workPlan, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(final Workplan workPlan, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("workPlan/list");
		result.addObject("workPlan", workPlan);
		result.addObject("fixUpTask", workPlan);
		result.addObject("phase", workPlan);
		result.addObject("message", messageCode);

		return result;
	}

}
