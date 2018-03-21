package com.krishagni.crm.event;

public class CompanyListCriteria {
	private String name;
	
	private String contractType;
	
	private boolean exactMatch;
	
	private int startFrom;
	
	private int maxResults;
	
	public CompanyListCriteria self() {
		return this;
	}
	
	public String name() {
		return name;
	}

	public CompanyListCriteria name(String name) {
		this.name = name;
		return self();
	}

	public String contractType() {
		return contractType;
	}

	public CompanyListCriteria contractType(String contractType) {
		this.contractType = contractType;
		return self();
	}	
	
	public boolean exactMatch() {
		return exactMatch;
	}
	
	public CompanyListCriteria exactMatch(boolean exactMatch) {
		this.exactMatch = exactMatch;
		return self();
	}
	
	public int startFrom() {
		return startFrom;
	}
	
	public CompanyListCriteria startFrom(int startFrom) {
		this.startFrom = startFrom;
		return self();
	}
	
	public int maxResults() {
		return maxResults;
	}
	
	public CompanyListCriteria maxResults(int maxResults) {
		this.maxResults = maxResults;
		return self();
	}
}