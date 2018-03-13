package com.krishagni.crm.event;

import java.util.Date;
import com.krishagni.crm.domain.Company;

public class CompanyDetail {
	private int id;
	
	private String name;

	private String contractType;
	
	private int credits;
	
	private Date startDate;
	
	private Date endDate;
	
	private String notes;
	
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
	
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public static CompanyDetail from(Company company) {
		CompanyDetail detail = new CompanyDetail();
		detail.setId(company.getId());
		detail.setName(company.getName());
		detail.setContractType(company.getContractType().name());
		detail.setCredits(company.getCredits());
		detail.setStartDate(company.getStartDate());
		detail.setEndDate(company.getEndDate());
		detail.setNotes(company.getNotes());
		return detail;
	}
}