package com.krishagni.crm.services.impl;

import com.krishagni.crm.domain.Company;
import com.krishagni.crm.domain.Company.ContractType;
import com.krishagni.crm.domain.factory.CompanyFactory;
import com.krishagni.crm.dao.CompanyDao;
import com.krishagni.crm.exception.CRMException;
import com.krishagni.crm.services.CompanyService;
import com.krishagni.crm.event.CompanyDetail;
import com.krishagni.crm.event.CompanyListCriteria;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
	
	@Transactional
	public List<CompanyDetail> getCompanies(CompanyListCriteria criteria) {
		validateContractType(criteria);
		List<CompanyDetail> companies = dao.getCompanies(criteria);
		return companies;
	}
	
	private void ensureUniqueName(Company company) {
		company = dao.getCompany(company.getName());
		if (company == null) {
			return;
		}

		throw new CRMException(company.getName() + "Company already exists.");	
	}
	
	private void validateContractType(CompanyListCriteria criteria) {
		if (StringUtils.isBlank(criteria.contractType())) {
			return;
		}
		
		try {
			ContractType.valueOf(criteria.contractType().toUpperCase());
		} catch (IllegalArgumentException iae) {
			throw new CRMException("The contract type " + criteria.contractType() + " is invalid.");	
		}
	}
}