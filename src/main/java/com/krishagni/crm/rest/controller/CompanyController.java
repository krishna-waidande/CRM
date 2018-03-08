package com.krishagni.crm.rest.controller;

import com.krishagni.crm.event.CompanyDetail;
import com.krishagni.crm.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService companySvc;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getCompanyName() {
		return "Krishgni Solution"; 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public CompanyDetail createCompany(@RequestBody CompanyDetail detail) {
		return companySvc.createCompany(detail);
	}
}
