package com.xebia.automating_flyers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDayFromDate {

	public static String checkDay() {

		String day = "";
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely

		day = simpleDateformat.format(now);

		return day;

	}

}
