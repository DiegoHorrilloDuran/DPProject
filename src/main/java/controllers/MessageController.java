
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import domain.Message;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	@Autowired
	private MessageService	messageService;


	//send
	@RequestMapping("/send")
	public ModelAndView send() {
		ModelAndView result;
		Message msg;

		msg = this.messageService.create();

		result = this.createSendModelAndView(msg);

		return result;
	}

	// save

	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message msg, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createSendModelAndView(msg);
		else
			try {
				this.messageService.save(msg);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createSendModelAndView(msg, "msg.commit.error");
			}
		return result;
	}

	private ModelAndView createSendModelAndView(final Message msg) {
		return this.createSendModelAndView(msg, null);
	}

	private ModelAndView createSendModelAndView(final Message msg, final String message) {
		// TODO Auto-generated method stub
		return null;
	}

}
