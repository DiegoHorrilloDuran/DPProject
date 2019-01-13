package controllers;

import javax.validation.Valid;

import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.HandyWorkerService;

import domain.HandyWorker;

@Controller
@RequestMapping("/handyworker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService handyWorkerService;

	// Constructors -----------------------------------------------------------

	public HandyWorkerController() {
		super();
	}

	// Create ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		HandyWorker handyWorker;

		handyWorker = this.handyWorkerService.create();
		result = this.createEditModelAndView(handyWorker);

		return result;
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int handyWorkerId) {
		ModelAndView result;
		HandyWorker handyWorker;

		handyWorker = handyWorkerService.findOne(handyWorkerId);
		Assert.notNull(handyWorker);
		result = this.createEditModelAndView(handyWorker);

		return result;
	}

	// Save -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid HandyWorker handyWorker, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(handyWorker);
		} else {
			try {
				handyWorkerService.save(handyWorker);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(handyWorker, "handyWorker.commit.error");
			}
		}
		return result;
	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(HandyWorker handyWorker, BindingResult binding) {
		ModelAndView result;

		try {
			handyWorkerService.delete(handyWorker);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = this.createEditModelAndView(handyWorker, "handyWorker.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(HandyWorker handyWorker) {
		return createEditModelAndView(handyWorker, null);
	}

	protected ModelAndView createEditModelAndView(HandyWorker handyWorker, String msg) {
		ModelAndView result;
		result = new ModelAndView("handyWorker/edit");
		result.addObject("handyWorker", handyWorker);
		result.addObject("message", msg);
		return result;
	}

}
