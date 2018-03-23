package com.krishagni.crm.services;

import java.util.Map;

public interface EmailService {
	void sendMail(String tmplkey, String subject, String[] to, Map<String,Object> prop);
}
