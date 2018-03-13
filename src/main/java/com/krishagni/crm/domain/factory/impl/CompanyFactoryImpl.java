package com.krishagni.crm.domain.factory.impl;

import com.krishagni.crm.exception.CRMException;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.domain.Company.ContractType;
import com.krishagni.crm.domain.factory.CompanyFactory;
import com.krishagni.crm.event.CompanyDetail;
import org.apache.commons.lang3.StringUtils;

public class CompanyFactoryImpl implements CompanyFactory {
	public Company createCompany(CompanyDetail detail) {
		Company company =  new Company();
		CRMException crmEx = new CRMException();
		
		setName(detail, company, crmEx);
		setContractType(detail, company, crmEx);
		setCredits(detail, company, crmEx);
		setStartDate(detail, company, crmEx);
		setEndDate(detail, company, crmEx);
		setNotes(detail, company, crmEx);
		crmEx.checkAndThrow();
		return company;
	}
	
	private void setName(CompanyDetail detail, Company company, CRMException crmEx) {
		if (StringUtils.isBlank(detail.getName())) {
			crmEx.addError("Company name can't be empty.");
			return;
		}
		
		company.setName(detail.getName());	
	}
		
	private void setContractType(CompanyDetail detail, Company company, CRMException crmEx) {
		String contractType = detail.getContractType();
		
		if (StringUtils.isBlank(contractType)) {
			company.setContractType(ContractType.CUSTOM);
			return;
		}
		
		try {
			company.setContractType(ContractType.valueOf(contractType));
		} catch (IllegalArgumentException iae) {
			crmEx.addError("The contract type " + contractType + " is invalid.");
		}
	
     }
	
	private void setCredits(CompanyDetail detail, Company company, CRMException crmEx) {
		if (detail.getCredits() <= 0) {
			crmEx.addError("Credits should not be less than or equal to zero.");
			return;
		}
		
		company.setCredits(detail.getCredits());
	}
	
	private void setStartDate(CompanyDetail detail, Company company, CRMException crmEx) {
		if (detail.getStartDate() == null) {
			crmEx.addError("Start Date is required.");
			return;
		}
		
		company.setStartDate(detail.getStartDate());
	}
	
	private void setEndDate(CompanyDetail detail, Company company, CRMException crmEx) {
		if (detail.getEndDate() == null) {
			crmEx.addError("End Date is required.");
			return;
		}
		
		if (detail.getStartDate() != null && detail.getEndDate().before(detail.getStartDate())) {
			crmEx.addError("End Date should be greater than Start Date.");
			return;
		}
		
		company.setEndDate(detail.getEndDate());
	}
	
	private void setNotes(CompanyDetail detail, Company company, CRMException crmEx) {
		company.setNotes(detail.getNotes());
	}
}
