package com.krishagni.crm.domain.factory;

import com.krishagni.crm.domain.JiraTicket;
import com.krishagni.crm.event.TicketDetail;
import com.krishagni.crm.domain.Company;

public interface TicketFactory {
	JiraTicket createTicket(TicketDetail.Issue issue,Company company);
}
