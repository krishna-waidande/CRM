package com.krishagni.crm.domain.factory.impl;

import com.krishagni.crm.domain.Company;
import org.springframework.util.StringUtils;
import com.krishagni.crm.event.CompanyDetail;
import com.krishagni.crm.exception.CRMException;
import com.krishagni.crm.domain.factory.CompanyFactory;

public class CompanyFactoryImpl implements CompanyFactory {
	public Company createCompany(CompanyDetail detail) {
		Company company =  new Company();
		CRMException crmEx = new CRMException();
		setName(detail, company, crmEx);
		crmEx.checkAndThrow();
		return company;
	}
	
	private void setName(CompanyDetail detail, Company company, CRMException crmEx) {
		if (StringUtils.isEmpty(detail.getName())) {
			crmEx.addError("Company name can't be empty");
		}
		company.setName(detail.getName());
	}
}
