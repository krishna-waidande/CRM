package com.krishagni.crm.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import com.krishagni.crm.dao.CompanyDao;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.domain.JiraTicket;
import com.krishagni.crm.domain.factory.TicketFactory;
import com.krishagni.crm.event.TicketDetail;
import com.krishagni.crm.services.EmailService;
import com.krishagni.crm.services.TicketService;

public class TicketServiceImpl implements TicketService {
	private CompanyDao dao;
	
	private TicketFactory ticketFactory;
	
	private EmailService emailSvc;
	
	public void setDao(CompanyDao dao) {
		this.dao = dao;
	}

	public void setTicketFactory(TicketFactory ticketFactory) {
		this.ticketFactory = ticketFactory;
	}
	
	public void setEmailSvc(EmailService emailSvc) {
		this.emailSvc = emailSvc;
	}

	public void generateTickets(TicketDetail ticketDetail) {
		HashSet<String> untrackedCompanies = new HashSet<String>(); 
		List<JiraTicket> tickets = new ArrayList<JiraTicket>();
		
		for (TicketDetail.Issue issue : ticketDetail.getIssues()) {
			String companyName = issue.getFields().getSecurity().get(COMPANY_NAME);
			Company company = dao.getCompanyByName(companyName);
			if (company != null) {
				JiraTicket ticket = ticketFactory.createTicket(issue, company);
				tickets.add(ticket);
			} else {
				untrackedCompanies.add(companyName +"\n");
			}
		}
		saveTickets(tickets);
		sendReportToAdmin(untrackedCompanies);
	}
	
	private void sendReportToAdmin(HashSet<String> report) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("reports", report);
		String[] to = new String[] {"krishnawaidande1512@gmail.com", "ktgnair95@gmail.com"}; 
		emailSvc.sendMail(UNTRACKED_COMPANY_MAIL_TEMPLATE, UNTRACKED_COMPANY_MAIL_SUBJECT, to, properties);
	}

	private void saveTickets(List<JiraTicket> tickets) {
		for (JiraTicket ticket : tickets) {
			dao.saveTicket(ticket);
		}
	}
	
	private static final String UNTRACKED_COMPANY_MAIL_SUBJECT = "Untracked tickets company list";
	
	private static final String UNTRACKED_COMPANY_MAIL_TEMPLATE = "untracked_tickets_company_list.vm";
	
	private static final String COMPANY_NAME = "name";
}
