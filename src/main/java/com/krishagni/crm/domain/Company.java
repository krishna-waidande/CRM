package com.krishagni.crm.domain;

import java.util.Date;
import com.krishagni.crm.common.util.Status;
import com.krishagni.crm.common.util.Utility;

public class Company {
	public enum ContractType {
		GOLD, 
		SILVER, 
		BRONZE, 
		CUSTOM
	};
	
	private Long id;
	
	private String name;
	
	private ContractType contractType;
	
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
	
	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
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

	public void update(Company company) {
		this.setName(company.getName());
		this.setContractType(company.getContractType());
		this.setCredits(company.getCredits());
		this.setStartDate(company.getStartDate());
		this.setEndDate(company.getEndDate());
		this.setNotes(company.getNotes());
		setActivityStatus(company.getStatus());
	}

	public void delete() {
		this.setName(Utility.getDisabledValue(this.getName(), 255));
		this.setStatus(Status.COMPANY_STATUS_DISABLED);
	}
	
	private void setActivityStatus(String status) {
		if (this.status.equals(status)) {
			return;
		}
		
		if (status.equals(Status.COMPANY_STATUS_DISABLED)) {
			delete();
		} else {
			this.setStatus(status);
		}
	}
}