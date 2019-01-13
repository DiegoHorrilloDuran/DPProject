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


import domain.Tutorial;



import services.TutorialService;


@Controller
@RequestMapping("/tutorials")
public class TutorialController extends AbstractController {

	@Autowired
	private TutorialService tutorialService;
	
	
	// Constructors -----------------------------------------------------------

	public TutorialController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/create", method= RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Tutorial tutorial;

		tutorial = this.tutorialService.create() ;
		

		result = this.createEditModelAndView(tutorial);
		
		return result;
	}
	@RequestMapping(value = "/list", method= RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Tutorial> tutorials;
		
		tutorials= tutorialService.findAll();
		
		res= new ModelAndView("tutorial/list");
		res.addObject("tutorials",tutorials);
		res.addObject("requestURI", "tutorial/list.do");
		return res;
	}
	@RequestMapping(value = "/update", method= RequestMethod.GET)
	public ModelAndView update(@RequestParam int tutorialId) {
		ModelAndView result;
		Tutorial tutorial;
		
		tutorial= this.tutorialService.findOne(tutorialId);
		Assert.notNull(tutorial);
		result=createEditModelAndView(tutorial);
		
		return result;
	}
	@RequestMapping(value = "/update", method= RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Tutorial tutorial, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()){
			result=createEditModelAndView(tutorial);
		}else{
			try{
				this.tutorialService.save(tutorial);
				result= new ModelAndView("redirect:list.do");
			} catch(Throwable oops){
				result= createEditModelAndView(tutorial, "tutorial.commit.error");
			}
			
		}
		return result;
		}
	
	@RequestMapping(value = "/update", method= RequestMethod.POST, params="delete")
	public ModelAndView delete(Tutorial tutorial, BindingResult binding) {
		ModelAndView result;
		
		try{
			tutorialService.delete(tutorial);
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result=createEditModelAndView( tutorial,"tutorial.commit.error");
			
		}
		return result;
		}
	protected ModelAndView createEditModelAndView(Tutorial tutorial){
		ModelAndView result;
		
		result=createEditModelAndView(tutorial, null);
		
		return result;
	}
	protected ModelAndView createEditModelAndView(Tutorial tutorial, String messageCode){
		ModelAndView result;
		
		result = new ModelAndView("tutorial/update");
		result.addObject("tutorial", tutorial);
		result.addObject("message",messageCode);
		return result;
	}
		
	
	
		
		
}
