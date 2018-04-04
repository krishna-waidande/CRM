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
import com.krishagni.crm.event.CompanyListCriteria;
import com.krishagni.crm.services.EmailService;
import com.krishagni.crm.services.TicketService;

public class TicketServiceImpl implements TicketService{
	private EmailService emailSvc;
	
	private CompanyDao dao;	
	
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
		CompanyListCriteria criteria = new CompanyListCriteria();
		List<Company> companies = dao.getCompanies(criteria);
		String[] to = new String[] {"ktgnair95@gmail.com", "krishnawaidande1512@gmail.com"};
		for (Company company : companies) {
			int totalCreditsUsed = 0;
			Set<JiraTicket> tickets = company.getTickets();
			for (JiraTicket jiraTicket : tickets) {
				totalCreditsUsed = totalCreditsUsed + jiraTicket.getCreditsUsed();
			}
			int remainingCredits = company.getCredits() - totalCreditsUsed;
			Map<String, Object> properties = new HashMap<>();
			properties.put("company", company);
			properties.put("remainingCredits", remainingCredits);
			properties.put("credits", totalCreditsUsed);
			emailSvc.sendMail(CREDITS_SUMMARY_MAIL_TEMPLATE, CREDITS_SUMMARY_MAIL_SUBJECT, to, properties);
		}
	}
	private static final String CREDITS_SUMMARY_MAIL_SUBJECT = "OpenSpecimen: Support credit summary";
	
	private static final String CREDITS_SUMMARY_MAIL_TEMPLATE = "credit_summary.vm";	
}
