package com.krishagni.crm.dao.impl;

import java.util.Date;	
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.domain.Company.ContractType;
import com.krishagni.crm.event.CompanyDetail;
import com.krishagni.crm.event.CompanyListCriteria;
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
		return (Company) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Company>  getContractExpiringCompanies(Date date) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(GET_CONTRACT_EXPIRING_CMPS);
		query.setParameter("date", date);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyDetail> getCompanies(CompanyListCriteria criteria) {
		Criteria query = sessionFactory.getCurrentSession()
				.createCriteria(Company.class)
				.setMaxResults(criteria.maxResults())
				.setFirstResult(criteria.startFrom())
				.addOrder(Order.asc("name"));
					
		addSearchConditions(query, criteria);
		return query.list();
	}
	
	private Criteria addSearchConditions(Criteria query, CompanyListCriteria criteria) {
		addNameRestrictions(query, criteria);
		addContractTypeRestrictions(query, criteria);
		return query;
	}
	
	private Criteria addNameRestrictions(Criteria query, CompanyListCriteria criteria) {
		if (StringUtils.isBlank(criteria.name())) {
	 		return query;
		}	
			
		if (!criteria.exactMatch()) {
			query.add(Restrictions.ilike("name", criteria.name(), MatchMode.ANYWHERE));
		} else {
			query.add(Restrictions.eq("name", criteria.name()));
		}
		
		return query;
	}
	
	private Criteria addContractTypeRestrictions(Criteria query, CompanyListCriteria criteria) {
		if (StringUtils.isBlank(criteria.contractType())) {
	 		return query;
		}	
		
		if (StringUtils.isNotBlank(criteria.contractType())) {
			query.add(Restrictions.eq("contractType", ContractType.valueOf(criteria.contractType().toUpperCase())));
		}
	
		return query;
	}

	private static final String FQN = Company.class.getName();
	
	private static final String GET_COMPANY_NAME = FQN + ".getCompanyName";
	
	private static final String GET_CONTRACT_EXPIRING_CMPS = FQN + ".getContractExpiringCompanies";
}
