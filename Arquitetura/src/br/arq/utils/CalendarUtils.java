package br.arq.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarUtils {
	public static int getAnoAtual() {
		Calendar calendar = GregorianCalendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
}
