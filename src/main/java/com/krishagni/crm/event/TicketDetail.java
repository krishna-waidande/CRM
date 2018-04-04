package com.krishagni.crm.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketDetail {
	private List<Issue> issues = new ArrayList<Issue>();
	
	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public static class Issue {
		private String key;
		
		private Fields fields;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Fields getFields() {
			return fields;
		}

		public void setFields(Fields fields) {
			this.fields = fields;
		}
	}

	public static class Fields {
		private int customfield_10500;
		
		private String resolutiondate;
		
		private String summary;
		
		private Map<String,String> security;
		
		public int getCustomfield_10500() {
			return customfield_10500;
		}

		public void setCustomfield_10500(int customfield_10500) {
			this.customfield_10500 = customfield_10500;
		}

		public String getResolutiondate() {
			return resolutiondate;
		}

		public void setResolutiondate(String resolutiondate) {
			this.resolutiondate = resolutiondate;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public Map<String, String> getSecurity() {
			return security;
		}

		public void setSecurity(Map<String, String> security) {
			this.security = security;
		}
	}
}
