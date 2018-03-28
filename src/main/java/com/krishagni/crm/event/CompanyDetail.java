package com.krishagni.crm.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.krishagni.crm.domain.Company;

public class CompanyDetail {
	private Long id;
	
	private String name;

	private String contractType;
	
	private int credits;
	
	private Date startDate;
	
	private Date endDate;
	
	private String notes;
	
	private String status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		detail.setStatus(company.getStatus());
		return detail;
	}	

	public static List<CompanyDetail> from(List<Company> companies) {
		List<CompanyDetail> result = new ArrayList<>();
		for (Company company : companies) {
			result.add(CompanyDetail.from(company));
		}
		return result;
	}
}
