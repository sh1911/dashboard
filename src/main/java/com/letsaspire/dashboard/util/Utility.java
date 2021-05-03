package com.letsaspire.dashboard.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
	public static String getCurrentDateAndTimes() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(dtf.format(now));
		return dtf.format(now);

	}

	public static String getCurrentDateAndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		// Here you say to java the initial timezone. This is the secret
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		// Will print in UTC
		// System.out.println("Current Time Stamp : " + sdf.format(calendar.getTime()));
		String formattedDate = sdf.format(calendar.getTime());
		return formattedDate;
	}

	public long getGapInDays(String deDate) {
		long gap = -999;
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = getCurrentDateAndTime();
		try {
			Date date1 = myFormat.parse(deDate);
			Date date2 = myFormat.parse(currentDate);
			long diff = date2.getTime() - date1.getTime();
			gap = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return gap;
	}
}