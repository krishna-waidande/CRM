package com.krishagni.crm.dao;

import com.krishagni.crm.domain.Company;

public interface CompanyDao {
	Company saveCompany(Company company);
	
	Company getCompany(String name);
}
