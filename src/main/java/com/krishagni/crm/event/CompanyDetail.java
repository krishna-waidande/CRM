package com.krishagni.crm.event;

import com.krishagni.crm.domain.Company;

public class CompanyDetail {
	private int id;
	
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static CompanyDetail from(Company company) {
		CompanyDetail detail = new CompanyDetail();
		detail.setId(company.getId());
		detail.setName(company.getName());
		return detail;
	}	
}
