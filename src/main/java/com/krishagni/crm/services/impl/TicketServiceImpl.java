package com.krishagni.crm.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import com.krishagni.crm.dao.CompanyDao;
import com.krishagni.crm.domain.Company;
import com.krishagni.crm.domain.JiraTicket;
import com.krishagni.crm.services.EmailService;
import com.krishagni.crm.services.TicketService;

public class TicketServiceImpl implements TicketService{
	EmailService emailSvc;
	
	CompanyDao dao;
	
	public void setEmailSvc(EmailService emailSvc) {
		this.emailSvc = emailSvc;
	}
	
	public void setDao(CompanyDao dao) {
		this.dao = dao;
	}
	
	@Transactional
	@Scheduled(cron = "0 0/1 * * * ?")
	public void generateReport() {
		getTickets();
	}

	public void getTickets() {
		List<Company> companies = dao.getAllCompany();
		int remainingCredits = 0,totalCreditsUsed = 0;
		
		for (Company company : companies) {
			Set<JiraTicket> tickets = company.getTickets();
			for (JiraTicket t : tickets) {
				totalCreditsUsed = totalCreditsUsed + t.getCreditsUsed();
			}
			remainingCredits = company.getCredits() - totalCreditsUsed;
			Map<String, Object> properties = new HashMap<>();
			properties.put("company", company);
			properties.put("remainingCredits", remainingCredits);
			properties.put("credits", totalCreditsUsed);
			String[] to = new String[] {"ktgnair95@gmail.com", "krishnawaidande1512@gmail.com"};
			emailSvc.sendMail(JIRA_TICKET_MAIL_TEMPLATE, JIRA_TICKET_MAIL_SUBJECT, to, properties);
		}
	}
	private static final String JIRA_TICKET_MAIL_SUBJECT = "OpenSpecimen: Support credit summary";
	
	private static final String JIRA_TICKET_MAIL_TEMPLATE = "jira_ticket_summary.vm";	
}
