package com.bjss.basket.pricer.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

	public static Date getFirstDayOfWeek(Date date){
		Calendar firstDayOfWeek = Calendar.getInstance();
		firstDayOfWeek.setTime(date);
		firstDayOfWeek.set(Calendar.HOUR_OF_DAY, 0);
		firstDayOfWeek.clear(Calendar.MINUTE);
		firstDayOfWeek.clear(Calendar.SECOND);
		firstDayOfWeek.clear(Calendar.MILLISECOND);
		firstDayOfWeek.set(Calendar.DAY_OF_WEEK, firstDayOfWeek.getFirstDayOfWeek());
		return firstDayOfWeek.getTime();
	}
	
	public static Date getFirstDayOfNextWeek(Date date){
		Calendar firstDayOfWeek = Calendar.getInstance();
		firstDayOfWeek.setTime(getFirstDayOfWeek(date));
		firstDayOfWeek.add(Calendar.WEEK_OF_YEAR, 1);
		return firstDayOfWeek.getTime();
	}
}
