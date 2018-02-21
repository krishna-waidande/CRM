package com.krishagni.CRM.rest.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {	
	@ResponseBody
	@RequestMapping(value="/company", method = RequestMethod.GET)
	public String getCompanyName() {
		return "Krishgni Solution"; 
	}
	
	@ResponseBody
	@RequestMapping(value="/company", method=RequestMethod.POST)
	public void createCompany(@RequestBody CompanyDetail company) {
		System.out.println(company.getCompanyName());
	}
}
