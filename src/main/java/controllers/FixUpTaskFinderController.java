package controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FixUpTaskFinderService;
import services.HandyWorkerService;
import controllers.customer.FixUpTaskCustomerController;
import domain.FixUpTaskFinder;
@Controller
@RequestMapping("/fixUpTaskFinder/worker")
public class FixUpTaskFinderController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private FixUpTaskFinderService finderService;
	@Autowired
	private HandyWorkerService handyWorkerService;
	@Autowired
	private FixUpTaskCustomerController fixUpTaskCustomerController;

	// Constructors -----------------------------------------------------------

	public FixUpTaskFinderController() {
		super();
	}

	// Listing ----------------------------------------------------------------


	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		FixUpTaskFinder fixUpTaskFinder;

		fixUpTaskFinder = this.finderService.create();
		result = this.createEditModelAndView(fixUpTaskFinder);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		FixUpTaskFinder fixUpTaskFinder = null;
        for (FixUpTaskFinder f : this.handyWorkerService.findByPrincipal().getFixUpTaskFinders()){
        	fixUpTaskFinder = f;
        }
		Assert.notNull(fixUpTaskFinder);
		result = this.createEditModelAndView(fixUpTaskFinder);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FixUpTaskFinder fixUpTaskFinder, final BindingResult binding) {
		ModelAndView result;
		FixUpTaskFinder finderDef;
		if (binding.hasErrors())
			result = this.createEditModelAndView(fixUpTaskFinder);
		else
			try {
				finderDef = this.finderService.save(fixUpTaskFinder);
				result = this.fixUpTaskCustomerController.listFinder(finderDef.getSearchResult().getFixUpTasks());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(fixUpTaskFinder, "task.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final FixUpTaskFinder fixUpTaskFinder, final BindingResult binding) {
		ModelAndView result;

		try {
			this.finderService.delete(fixUpTaskFinder);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(fixUpTaskFinder, "task.commit.error");
		}

		return result;
	}

	// Showing -----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final FixUpTaskFinder fixUpTaskFinder) {
		ModelAndView result;

		result = this.createEditModelAndView(fixUpTaskFinder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final FixUpTaskFinder fixUpTaskFinder, final String message) {
		ModelAndView result;
		if(fixUpTaskFinder.getId() == 0) {
			result = new ModelAndView("fixUpTaskFinder/create");
		} else {
			result = new ModelAndView("fixUpTaskFinder/edit");
		}
		result.addObject("fixUpTaskFinder", fixUpTaskFinder);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView showModelAndView(final FixUpTaskFinder fixUpTaskFinder) {
		ModelAndView result;
		result = new ModelAndView("fixUpTaskFinder/edit");
		result.addObject("fixUpTaskFinder", fixUpTaskFinder);

		return result;
	}

}
