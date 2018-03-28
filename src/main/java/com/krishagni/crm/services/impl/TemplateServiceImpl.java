package com.krishagni.crm.services.impl;

import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.ui.velocity.VelocityEngineUtils;
import com.krishagni.crm.exception.CRMException;
import com.krishagni.crm.services.TemplateService;

public class TemplateServiceImpl implements TemplateService {
	private VelocityEngine velocityEngine;

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public String render(String templateName, Map<String, Object> properties) {
		try {
			return VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), templateName, "utf-8", properties);
		} catch (VelocityException ex) {
			throw new CRMException(ex.getMessage());
		}
	}
}
