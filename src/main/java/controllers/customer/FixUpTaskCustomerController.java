
package controllers.customer;

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

import services.CategoryService;
import services.FixUpTaskService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Category;
import domain.FixUpTask;
import domain.Warranty;

@Controller
@RequestMapping("/fixUpTask/customer")
public class FixUpTaskCustomerController extends AbstractController {

	@Autowired
	private FixUpTaskService	fixUpTaskService;

	@Autowired
	private CategoryService		categoryService;

	@Autowired
	private WarrantyService		warrantyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<FixUpTask> fixUpTasks;

		fixUpTasks = this.fixUpTaskService.findAll();

		result = new ModelAndView("fixUpTask/list");
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("requestURI", "fixUpTask/customer/list.do");

		return result;

	}
	
	public ModelAndView listFinder(Collection<FixUpTask> fut) {
		ModelAndView result;

		result = new ModelAndView("fixUpTask/list");
		result.addObject("fixUpTasks", fut);
		result.addObject("requestURI", "fixUpTask/customer/list.do");

		return result;

	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		FixUpTask fixUpTask;

		fixUpTask = this.fixUpTaskService.create();
		result = this.createEditModelAndView(fixUpTask);

		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int fixUpTaskId) {
		ModelAndView result;
		FixUpTask fixUpTask;

		fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);
		Assert.notNull(fixUpTask);
		result = this.createEditModelAndView(fixUpTask);

		return result;

	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid FixUpTask fixUpTask, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(fixUpTask);
		} else {
			try {
				this.fixUpTaskService.save(fixUpTask);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				// TODO: handle exception
				result = this.createEditModelAndView(fixUpTask, "fixUpTask.commit.error");
			}

		}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(FixUpTask fixUpTask, BindingResult binding) {

		ModelAndView result;

		try {
			this.fixUpTaskService.delete(fixUpTask);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = this.createEditModelAndView(fixUpTask, "fixUpTask.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(FixUpTask fixUpTask) {
		ModelAndView result;

		result = this.createEditModelAndView(fixUpTask, null);
		return result;
	}
	protected ModelAndView createEditModelAndView(FixUpTask fixUpTask, String messageCode) {

		ModelAndView result;
		Collection<Category> categories;
		Collection<Warranty> warranties;

		categories = this.categoryService.findAll();
		warranties = this.warrantyService.findAll();

		result = new ModelAndView("fixUpTask/edit");
		result.addObject("fixUpTask", fixUpTask);
		result.addObject("categories", categories);
		result.addObject("warranties", warranties);

		result.addObject("message", messageCode);

		return result;

	}
	

}
