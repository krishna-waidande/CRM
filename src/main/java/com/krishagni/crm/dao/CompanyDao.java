package com.krishagni.crm.dao;

import java.util.Date;
import java.util.List;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.event.CompanyListCriteria;

public interface CompanyDao {
	Company getCompanyById(Long id);
	
	Company getCompanyByName(String name);
	
	//List<Company> getAllCompany();
	
	List<Company> getCompanies(CompanyListCriteria criteria);
	
	List<Company> getContractExpiringCompanies(Date date);
		
	Company saveCompany(Company company);
}