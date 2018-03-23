package com.krishagni.crm.services;

import java.util.Map;

public interface TemplateService {
	String render(String templateName, Map<String,Object> props);
}
