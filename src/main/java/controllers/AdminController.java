
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import domain.Admin;

@Controller
@RequestMapping("/administrator")
public class AdminController extends AbstractController {

	@Autowired
	private AdminService	adminService;


	// Constructors -----------------------------------------------------------

	public AdminController() {
		super();
	}

	// Create ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Admin admin;

		admin = this.adminService.create();
		result = this.createEditModelAndView(admin);

		return result;
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int adminId) {
		ModelAndView result;
		Admin admin;

		admin = this.adminService.findOne(adminId);
		Assert.notNull(admin);
		result = this.createEditModelAndView(admin);

		return result;
	}

	// Save -------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Admin admin, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(admin);
		else
			try {
				this.adminService.save(admin);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(admin, "admin.commit.error");
			}
		return result;
	}

	// Delete -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Admin admin, final BindingResult binding) {
		ModelAndView result;

		try {
			this.adminService.delete(admin);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(admin, "admin.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Admin admin) {
		return this.createEditModelAndView(admin, null);
	}

	protected ModelAndView createEditModelAndView(final Admin admin, final String msg) {
		ModelAndView result;
		result = new ModelAndView("administrator/edit");
		result.addObject("admin", admin);
		result.addObject("message", msg);
		return result;
	}

}
