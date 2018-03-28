package com.krishagni.crm.common.util;

import java.util.Calendar;

public class Utility {
	public static String getDisabledValue(String name, int maxLength) {
		int maxAllowed = maxLength - 14;
		if (name.length() > maxAllowed) {
			name = name.substring(0, maxAllowed);
		}
		return name + "_" + Calendar.getInstance().getTimeInMillis();
	}
}