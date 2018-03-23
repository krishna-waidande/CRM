package com.krishagni.crm.common.util;

public class Status {

	public static final String COMPANY_STATUS_ACTIVE = "ACTIVE";

	public static final String COMPANY_STATUS_DISABLED = "DISABLED";

	public static final String COMPANY_STATUS_CLOSED = "CLOSED";

	public static boolean isValidActivityStatus(String status) {
		return status.equalsIgnoreCase(COMPANY_STATUS_ACTIVE) || 
		       status.equalsIgnoreCase(COMPANY_STATUS_DISABLED) ||
		       status.equalsIgnoreCase(COMPANY_STATUS_CLOSED);
	}
}
