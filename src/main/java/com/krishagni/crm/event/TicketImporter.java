package com.krishagni.crm.event;

import java.io.File;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krishagni.crm.dao.CompanyDao;
import com.krishagni.crm.services.TicketService;

public class TicketImporter {
	private CompanyDao dao;
	
	private TicketService ticketSvc;
	
	public void setDao(CompanyDao dao) {
		this.dao = dao;
	}
	
	public void setTicketSvc(TicketService ticketSvc) {
		this.ticketSvc = ticketSvc;
	}

	@Transactional
	@Scheduled(cron = "0 0 12 L * ?")
	public void loadTicketsAndsaveInDataBase() {
		clearTickets();
		importJson();
	}

	public void clearTickets() {
		dao.truncateTable();
	}

	public void importJson() {
		File jiraJson = new File("/home/user/Downloads/JIRA.json");
		ObjectMapper jsonToJavaMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			TicketDetail ticketDetail = jsonToJavaMapper.readValue(jiraJson, TicketDetail.class);
			ticketSvc.generateTickets(ticketDetail);
		} catch (Exception exeption) {
			System.out.println(exeption.getMessage());
		}
	}

}
