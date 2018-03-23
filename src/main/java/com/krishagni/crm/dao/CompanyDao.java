package com.krishagni.crm.dao;

import java.util.Date;
import java.util.List;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.event.CompanyDetail;
import com.krishagni.crm.event.CompanyListCriteria;

public interface CompanyDao {
	Company saveCompany(Company company);

	Company getCompany(String name);

	List<Company> getContractExpiringCompanies(Date date);
	
	List<CompanyDetail> getCompanies(CompanyListCriteria criteria);
}
