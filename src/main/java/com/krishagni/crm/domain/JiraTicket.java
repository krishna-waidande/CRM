package com.krishagni.crm.domain;

public class JiraTicket {
	private int id;
	
	private String jiraId;
	
	private String resolveDate;
	
	private String summary;
	
	private Company company;
	
	private int creditsUsed;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public String getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(String resolveDate) {
		this.resolveDate = resolveDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getCreditsUsed() {
		return creditsUsed;
	}

	public void setCreditsUsed(int creditsUsed) {
		this.creditsUsed = creditsUsed;
	}
}