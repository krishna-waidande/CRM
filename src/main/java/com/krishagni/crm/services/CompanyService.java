package com.krishagni.crm.services;

import java.util.Date;
import java.util.List;
import com.krishagni.crm.event.CompanyDetail;
import com.krishagni.crm.event.CompanyListCriteria;

public interface CompanyService {
	CompanyDetail createCompany(CompanyDetail detail);

	CompanyDetail updateCompany(CompanyDetail detail);
	
	List<CompanyDetail> getCompanies(CompanyListCriteria criteria);

	CompanyDetail deleteCompany(Long id);

	void notifyContractExpiringCmps();
	
	void notifyContractExpiringCmps(Date date);
	
	void loadJson();
}