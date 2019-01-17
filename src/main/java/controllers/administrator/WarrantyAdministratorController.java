package controllers.administrator;

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
import services.WarrantyService;
import controllers.AbstractController;
import domain.FixUpTask;
import domain.Warranty;

@Controller
@RequestMapping("/warranty/administrator")
public class WarrantyAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private WarrantyService		warrantyService;
	@Autowired
	private FixUpTaskService		fixUpTaskService;

	// Constructors -----------------------------------------------------------

	public WarrantyAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Warranty> warranties;

		warranties = this.warrantyService.findAll();
		result = new ModelAndView("warranty/list");
		result.addObject("requestURI", "warranty/administrator/list.do");
		result.addObject("warranties", warranties);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int id){
		ModelAndView result;
		Warranty warranty;

		FixUpTask f = this.fixUpTaskService.findOne(id);
		warranty = this.warrantyService.create(f);
		result = this.createEditModelAndView(warranty);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		Warranty warranty;

		warranty = this.warrantyService.findOne(id);
		Assert.notNull(warranty);
		result = this.createEditModelAndView(warranty);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Warranty warranty, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(warranty);
		else
			try {
				this.warrantyService.save(warranty);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(warranty, "warranty.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Warranty warranty, final BindingResult binding) {
		ModelAndView result;

		try {
			this.warrantyService.delete(warranty);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(warranty, "warranty.commit.error");
		}

		return result;
	}
	
	// Showing -----------------------------------------------------------------
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		ModelAndView result;
		Warranty warranty;

		warranty = this.warrantyService.findOne(id);
		Assert.notNull(warranty);
		result = this.showModelAndView(warranty);

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Warranty warranty) {
		ModelAndView result;

		result = this.createEditModelAndView(warranty, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Warranty warranty, final String message) {
		ModelAndView result;
		if(warranty.getId() == 0) {
			result = new ModelAndView("warranty/create");
		} else {
			result = new ModelAndView("warranty/edit");
		}
		result.addObject("warranty", warranty);
		result.addObject("message", message);

		return result;
	}
	
	protected ModelAndView showModelAndView(final Warranty warranty) {
		ModelAndView result;
		result = new ModelAndView("warranty/show");
		result.addObject("warranty", warranty);

		return result;
	}

}
