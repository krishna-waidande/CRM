package com.krishagni.crm.common.util;

import java.util.ArrayList;
import java.util.List;

import com.krishagni.crm.domain.Ticket;

public class JsonToJava {
	private List<Issue> issues = new ArrayList<Issue>();
	
	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public static class Issue {
		private String id;
		
		private String key;
		
		private Fields fields;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

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
		String resolutiondate;
		
		String summary;
		
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
	}
	
	public List<Ticket> generateTickets() {
		List<Ticket> tickets = new ArrayList<Ticket>();
		
		for (JsonToJava.Issue issue : this.getIssues()) {
			Ticket ticket = new Ticket();
			ticket.setJiraId(issue.getKey());
			ticket.setResolveDate(issue.getFields().getResolutiondate());
			ticket.setSummary(issue.getFields().getSummary());
			tickets.add(ticket);
		}
		return tickets;
	}
}

