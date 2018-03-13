package com.krishagni.crm.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.dao.CompanyDao;

public class CompanyDaoImpl implements CompanyDao {
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Company saveCompany (Company company) {
		sessionFactory.getCurrentSession().save(company);
		return company;
	}
	
	public Company getCompany(String name) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(GET_COMPANY_NAME);
		query.setParameter("name", name);
		Company company = (Company) query.uniqueResult();
		return company;
	}
	
	private static final String FQN = Company.class.getName();
	
	private static final String GET_COMPANY_NAME = FQN + ".getCompanyName";
}