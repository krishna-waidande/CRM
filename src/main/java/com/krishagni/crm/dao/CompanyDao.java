package com.krishagni.crm.dao;

import java.util.Date;
import java.util.List;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.event.CompanyListCriteria;

public interface CompanyDao {
	Company getCompany(Long id, String name);
	
	List<Company> getContractExpiringCompanies(Date date);
	
	List<Company> getCompanies(CompanyListCriteria criteria);
	
	Company saveCompany(Company company);
}