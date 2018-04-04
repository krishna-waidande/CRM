package com.krishagni.crm.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import com.krishagni.crm.dao.CompanyDao;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.domain.Company.ContractType;
import com.krishagni.crm.domain.factory.CompanyFactory;
import com.krishagni.crm.event.CompanyDetail;
import com.krishagni.crm.exception.CRMException;
import com.krishagni.crm.services.CompanyService;
import com.krishagni.crm.services.EmailService;
import com.krishagni.crm.event.CompanyListCriteria;
import org.apache.commons.lang3.StringUtils;

public class CompanyServiceImpl implements CompanyService {
	private CompanyFactory companyFactory;
	
	private CompanyDao dao;
	
	private EmailService emailSvc;
	
	public void setCompanyFactory(CompanyFactory companyFactory) {
		this.companyFactory = companyFactory;
	}

	public void setDao(CompanyDao dao) {
		this.dao = dao;
	}
	
	public void setEmailSvc(EmailService emailSvc) {
		this.emailSvc = emailSvc;
	}

	@Transactional
	public CompanyDetail createCompany(CompanyDetail detail) {
		Company company = companyFactory.createCompany(detail);
		ensureUniqueName(company.getName());
		company = dao.saveCompany(company);
		return CompanyDetail.from(company);
	}
	
	@Transactional
	public CompanyDetail updateCompany(CompanyDetail detail) {
		Company existing = getCompany(detail.getId(), detail.getName());
		
		Company company = companyFactory.createCompany(detail);
		
		if (!existing.getName().equals(company.getName())) {
			ensureUniqueName(company.getName());
		}
		existing.update(company);
		
		return CompanyDetail.from(existing);
	}
	
	@Transactional
	public List<CompanyDetail> getCompanies(CompanyListCriteria criteria) {
		validateContractType(criteria);
		List<Company> companies = dao.getCompanies(criteria);		
		return CompanyDetail.from(companies);
	}
	
	@Transactional
	@Override
	public CompanyDetail deleteCompany(Long id) {
		Company existing = getCompany(id, null);
		existing.delete();
		return CompanyDetail.from(existing);
	}

	@Transactional
	@Scheduled(cron = "0 * 22 * * ?")
	public void notifyContractExpiringCmps() {
		notifyContractExpiringCmps(null);
	}

	public void notifyContractExpiringCmps(Date date) {
		if (date == null) {
			Calendar calender = Calendar.getInstance();
			calender.add(Calendar.DATE, 90);
			date = calender.getTime();
		} 
		List<Company> companies = dao.getContractExpiringCompanies(date);
		notifyAdminForContractExpiry(companies);
	}

	private void notifyAdminForContractExpiry(List<Company> companies) {
		if (companies.isEmpty()) {
			return;
		}
		Map<String, Object> properties = new HashMap<>();
		properties.put("companies", companies);
		String[] to = new String[] {"krishnawaidande1512@gmail.com", "ktgnair95@gmail.com"}; 
		emailSvc.sendMail(CONTRACT_EXPIRY_MAIL_TEMPLATE, CONTRACT_EXPIRY_MAIL_SUBJECT, to, properties);	
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
	
	private void ensureUniqueName(String name) {
		Company company = dao.getCompanyByName(name);
		if (company != null) {
			throw new CRMException("Company with name '"+ name +"' already exist");
		}
	}

	private Company getCompany(Long id, String name) {
		Company company = null;
		Object key = null;
		
		if (id != 0) {
			company = dao.getCompanyById(id);
			key = id;
		} else if (StringUtils.isNotBlank(name)) {
			company = dao.getCompanyByName(name);
			key = name;
		}
		
		if (key == null) {
			throw new CRMException("Company id or name required");
		} else if (company == null) {
			throw new CRMException("Company '" + key + "' not exist.");
		}
		return company;
	}
	
	private static final String CONTRACT_EXPIRY_MAIL_SUBJECT = "List Of Expired Contract Companies";
	
	private static final String CONTRACT_EXPIRY_MAIL_TEMPLATE = "contract_expiry_notification.vm";
}
