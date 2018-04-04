package com.krishagni.crm.domain.factory.impl;

import com.krishagni.crm.domain.JiraTicket;
import com.krishagni.crm.domain.factory.TicketFactory;
import com.krishagni.crm.event.TicketDetail;
import com.krishagni.crm.domain.Company;

public class TicketFactoryImpl implements TicketFactory {
	public JiraTicket createTicket(TicketDetail.Issue issue, Company company) {
		JiraTicket ticket = new JiraTicket();
		ticket.setJiraId(issue.getKey());
		ticket.setResolveDate(issue.getFields().getResolutiondate());
		ticket.setSummary(issue.getFields().getSummary());
		ticket.setCompany(company);
		ticket.setCreditsUsed(issue.getFields().getCustomfield_10500());
		return ticket;
	}
}
