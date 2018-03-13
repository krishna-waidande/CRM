package com.krishagni.crm.services.impl;

import com.krishagni.crm.domain.Company;
import com.krishagni.crm.domain.factory.CompanyFactory;
import com.krishagni.crm.dao.CompanyDao;
import com.krishagni.crm.exception.CRMException;
import com.krishagni.crm.services.CompanyService;
import com.krishagni.crm.event.CompanyDetail;
import org.springframework.transaction.annotation.Transactional;

public class CompanyServiceImpl implements CompanyService {
	CompanyFactory companyFactory;
	
	CompanyDao dao;

	public void setCompanyFactory(CompanyFactory companyFactory) {
		this.companyFactory = companyFactory;
	}

	public void setDao(CompanyDao dao) {
		this.dao = dao;
	}

	@Transactional
	public CompanyDetail createCompany(CompanyDetail detail) {
		Company company = companyFactory.createCompany(detail);
		ensureUniqueName(company);
		company = dao.saveCompany(company);
		return CompanyDetail.from(company);
	}

	private void ensureUniqueName(Company company) {
		company = dao.getCompany(company.getName());
		if (company == null) {
			return;
		}

		throw new CRMException(company.getName() + " Company already exists.");	
	}
}