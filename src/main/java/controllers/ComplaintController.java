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

import domain.Complaint;


import services.ComplaintService;


@Controller
@RequestMapping("/complaint")
public class ComplaintController extends AbstractController {

	@Autowired
	private ComplaintService complaintService;
	
	
	// Constructors -----------------------------------------------------------

	public ComplaintController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/create", method= RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Complaint complaint;

		complaint = this.complaintService.create() ;
		

		result = this.createEditModelAndView(complaint);
		
		return result;
	}
	@RequestMapping(value = "/list", method= RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Complaint> complaints;
		
		complaints= complaintService.findAll();
		
		res= new ModelAndView("complaint/list");
		res.addObject("complaints",complaints);
		res.addObject("requestURI", "complaint/list.do");
		return res;
	}
	@RequestMapping(value = "/update", method= RequestMethod.GET)
	public ModelAndView update(@RequestParam int complaintId) {
		ModelAndView result;
		Complaint complaint;
		
		complaint= this.complaintService.findOne(complaintId);
		Assert.notNull(complaint);
		result=createEditModelAndView(complaint);
		
		return result;
	}
	@RequestMapping(value = "/update", method= RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Complaint complaint, BindingResult binding) {
		ModelAndView result;
		
		if(binding.hasErrors()){
			result=createEditModelAndView(complaint);
		}else{
			try{
				this.complaintService.save(complaint);
				result= new ModelAndView("redirect:list.do");
			} catch(Throwable oops){
				result= createEditModelAndView(complaint, "complaint.commit.error");
			}
			
		}
		return result;
		}
	
	@RequestMapping(value = "/update", method= RequestMethod.POST, params="delete")
	public ModelAndView delete(Complaint complaint, BindingResult binding) {
		ModelAndView result;
		
		try{
			complaintService.delete(complaint);
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result=createEditModelAndView( complaint,"complaint.commit.error");
			
		}
		return result;
		}
	protected ModelAndView createEditModelAndView(Complaint complaint){
		ModelAndView result;
		
		result=createEditModelAndView(complaint, null);
		
		return result;
	}
	protected ModelAndView createEditModelAndView(Complaint complaint, String messageCode){
		ModelAndView result;
		
		result = new ModelAndView("complaint/update");
		result.addObject("complaint", complaint);
		result.addObject("message",messageCode);
		return result;
	}
		
	
	
		
		
}
