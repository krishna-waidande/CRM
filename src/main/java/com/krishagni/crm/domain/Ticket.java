package com.krishagni.crm.domain;

public class Ticket {
	private String jiraId;
	
	private String resolveDate;
	
	private String summary;

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

}
