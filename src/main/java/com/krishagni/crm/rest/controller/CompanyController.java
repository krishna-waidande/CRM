package com.krishagni.crm.rest.controller;

import com.krishagni.crm.event.CompanyDetail;
import com.krishagni.crm.event.CompanyListCriteria;
import com.krishagni.crm.services.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService companySvc;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<CompanyDetail> getCompanies(
		@RequestParam(value = "name", required = false) 
		String name,
			
		@RequestParam(value = "contractType", required = false)
		String contractType,
		
		@RequestParam(value = "exactMatch", required = false, defaultValue = "false")
		boolean exactMatch,
	
		@RequestParam(value = "startFrom", required = false, defaultValue = "0")
		int startFrom,
		
		@RequestParam(value = "maxResults", required = false, defaultValue = "100") 
		int maxResults) {
		
		CompanyListCriteria criteria = new CompanyListCriteria()
			.name(name)
			.contractType(contractType)
			.exactMatch(exactMatch)
			.startFrom(startFrom)
			.maxResults(maxResults);
		
		return companySvc.getCompanies(criteria);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public CompanyDetail createCompany(@RequestBody CompanyDetail detail) {
		return companySvc.createCompany(detail);
	}
}