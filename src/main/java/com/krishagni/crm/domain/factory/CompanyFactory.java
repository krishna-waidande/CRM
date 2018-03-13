package com.krishagni.crm.domain.factory;

import com.krishagni.crm.domain.Company;
import com.krishagni.crm.event.CompanyDetail;

public interface CompanyFactory {
	Company createCompany(CompanyDetail detail);
}