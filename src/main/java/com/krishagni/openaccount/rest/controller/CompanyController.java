package com.krishagni.openaccount.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CompanyController {
	
	
	@ResponseBody
	@RequestMapping(value="/company" , method = RequestMethod.GET)
	public String getCompanyName()
	{
		return "Krishgni solution" ; 
	}
	
	
	
}
