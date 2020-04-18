package com.dsys.common.util;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateUtils implements JsonValueProcessor {
	static long now = System.currentTimeMillis();

	public static Date CurrTime = new Date(now);

	public static final String DATE_FMT_1 = "yyyy-MM-dd";

	private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd");

	private static final SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	private String format = "yyyy-MM-dd HH:mm:ss";

	public DateUtils() {
	}

	public DateUtils(String format) {
		this.format = format;
	}

	public static String DateToString(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String strDateTime = formater.format(date);
		return strDateTime;
	}

	public static String DateToStringText(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy");
		String strDateTime = formater.format(date);
		return strDateTime;
	}

	public static String DateToString(Date date, String pattern) throws Exception {
		String strDateTime = null;
		try {
			SimpleDateFormat formater = new SimpleDateFormat(pattern);
			strDateTime = formater.format(date);
		} catch (Exception ex) {
			throw ex;
		}
		return strDateTime;
	}

	public static Date YmdToDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}

	public static String communityDateToString(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd HH:mm:ss");
		String strDateTime = formater.format(date);
		return strDateTime;
	}

	public static String[] getWeekParams(String str, String pattern) {
		String[] three = new String[3];
		if (pattern == null || pattern.equals("")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat formater = null;

		try {
			formater = new SimpleDateFormat(pattern);
		} catch (Exception e) {

			formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		Date date = new Date();

		try {
			date = formater.parse(str);
		} catch (ParseException e) {

			System.out.println("Parse date Error in DateUtils");
		}
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(2);
		c.setTime(date);
		c.get(3);
		c.set(7, 2);
		c.set(11, c.getActualMinimum(11));
		c.set(12, c.getActualMinimum(12));
		c.set(13, c.getActualMinimum(13));
		c.set(14, c.getActualMinimum(14));
		three[0] = formater.format(c.getTime());
		c.set(7, 8);
		c.set(11, c.getActualMaximum(11));
		c.set(12, c.getActualMaximum(12));
		c.set(13, c.getActualMaximum(13));
		c.set(14, c.getActualMaximum(14));
		three[1] = formater.format(c.getTime());
		three[2] = String.valueOf(c.get(3));
		return three;
	}

	public static Date getDateWeekDays(Date date, int weekday, int type) throws Exception {
//		String format = "yyyy-MM-dd HH:mm:ss";
		int weekdayIs = dateToWeekDay(date, type);
//		String[] day = getWeekParams(DateToString(date, format), format);

		if (type == 0) {
			if (weekday > weekdayIs) {
				date.setTime(date.getTime() + ((weekday - weekdayIs) * 24 * 60 * 60 * 1000));
				return date;
			}
			if (weekday == weekdayIs)
				return date;
			if (weekday < weekdayIs) {
				date.setTime(date.getTime() + ((7 - weekdayIs + weekday) * 24 * 60 * 60 * 1000));
				return date;
			}
		} else {
			if (weekday > weekdayIs) {
				date.setTime(date.getTime() + ((weekday - weekdayIs) * 24 * 60 * 60 * 1000));
				System.out.println(dateToWeekDay(date, type));
				return date;
			}
			if (weekday == weekdayIs) {
				System.out.println(dateToWeekDay(date, type));
				return date;
			}
			if (weekday < weekdayIs) {
				date.setTime(date.getTime() + 604800000L);
				System.out.println(dateToWeekDay(date, type));
				return date;
			}
		}
		return date;
	}

	public static Date StringToDate(String str) {
		Date dateTime = null;
		try {
			if (str != null && !str.equals("")) {
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				dateTime = formater.parse(str);
			}

		} catch (Exception ex) {
			System.out.println(ex.toString());
		} finally {
		}

		return dateTime;
	}

	public static Timestamp StringToDateHMS(String str) throws Exception {
		Timestamp time = null;
		try {
			time = Timestamp.valueOf(str);
		} catch (Exception ex) {

			System.out.print(ex.toString());
		}

		return time;
	}

	public static Date getMinDateOfDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, calendar.getActualMinimum(11));
		calendar.set(12, calendar.getActualMinimum(12));
		calendar.set(13, calendar.getActualMinimum(13));
		calendar.set(14, calendar.getActualMinimum(14));

		return calendar.getTime();
	}

	public static Date getMaxDateOfDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(11, calendar.getActualMaximum(11));
		calendar.set(12, calendar.getActualMaximum(12));
		calendar.set(13, calendar.getActualMaximum(13));
		calendar.set(14, calendar.getActualMaximum(14));

		return calendar.getTime();
	}

	public static Date parseDate(String dateString, String DataFormat) {
		if (DataFormat == null)
			DataFormat = "yyyy-MM-dd";
		SimpleDateFormat fordate = new SimpleDateFormat(DataFormat);
		if (dateString == null || dateString.equals("")) {
			return null;
		}
		try {
			return fordate.parse(dateString);
		} catch (ParseException e) {
			System.out.println(e.getMessage());

			return null;
		}
	}

	public static Date parseSQLDate(String dateString) {
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd");
		if (dateString == null || dateString.equals(""))
			return null;
		try {
			Date d = fordate.parse(dateString);
			Date sd = new Date(d.getTime());
			return sd;
		} catch (ParseException e) {
			System.out.println(e.getMessage());

			return null;
		}
	}

	public static String addTwoTimeStr(String time1, String time2) {
		String returnStr = "00:00:00";
		if (time1 != null && !time1.equalsIgnoreCase("") && time2 != null && !time2.equalsIgnoreCase("")) {
			String lastHour, lastMin, lastSec, time1Array[] = time1.split(":");
			String[] time2Array = time2.split(":");
			int hour1 = (new Integer(time1Array[0])).intValue();
			int hour2 = (new Integer(time2Array[0])).intValue();
			int min1 = (new Integer(time1Array[1])).intValue();
			int min2 = (new Integer(time2Array[1])).intValue();
			int sec1 = (new Integer(time1Array[2])).intValue();
			int sec2 = (new Integer(time2Array[2])).intValue();

			int totalSec = sec1 + sec2;
			if (totalSec / 60 > 0) {
				min1 += totalSec / 60;
			}
			if (totalSec % 60 > 9) {
				lastSec = (new Integer(totalSec % 60)).toString();
			} else {

				lastSec = new String("0" + (new Integer(totalSec % 60)).toString());
			}

			int totalMin = min1 + min2;
			if (totalMin / 60 > 0) {
				hour1 += totalMin / 60;
			}
			if (totalMin % 60 > 9) {
				lastMin = (new Integer(totalMin % 60)).toString();
			} else {

				lastMin = new String("0" + (new Integer(totalMin % 60)).toString());
			}

			int totalHour = hour1 + hour2;
			if (totalHour % 24 > 9) {
				lastHour = (new Integer(totalHour % 24)).toString();
			} else {

				lastHour = new String("0" + (new Integer(totalHour % 24)).toString());
			}

			returnStr = String.valueOf(lastHour) + ":" + lastMin + ":" + lastSec;
		} else if (time1 != null && !time1.equalsIgnoreCase("")) {
			returnStr = time1.substring(0, 8);
		} else if (time2 != null && !time2.equalsIgnoreCase("")) {
			returnStr = time2.substring(0, 8);
		} else {

			returnStr = "00:00:00";
		}

		return returnStr;
	}

	private static synchronized DateFormat getOraDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	private static synchronized DateFormat getOraExtendDateTimeFormat() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_EXTENDED_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	private static synchronized DateFormat getNewDateToStr() {
		SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_EXTENDED_FORMAT.clone();
		theDateTimeFormat.setLenient(false);
		return theDateTimeFormat;
	}

	public static String getSystemCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return doTransform(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
	}

	private static String doTransform(int year, int month, int day) {
		StringBuffer result = new StringBuffer();
		result.append(String.valueOf(year)).append("-")
				.append((month < 10) ? ("0" + String.valueOf(month)) : String.valueOf(month)).append("-")
				.append((day < 10) ? ("0" + String.valueOf(day)) : String.valueOf(day));
		return result.toString();
	}

	public static synchronized String getDayBeforeToday() {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, -1);
		return doTransform(toString(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
	}

	public static synchronized String getDayBeforeToday(String dateStr) {
		Date date = toDayStartDate(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, -1);
		return doTransform(toString(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
	}

	public static synchronized String getDayAfterToday() {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, 1);
		return doTransform(toString(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
	}

	public static synchronized String getDayAfterToday(String dateStr) {
		Date date = toDayStartDate(dateStr);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(5, 1);
		return doTransform(toString(gc.getTime(), getOraExtendDateTimeFormat())).substring(0, 10);
	}

	public static synchronized Date getDayAfterMonth(int months) {
		Date date = new Date(System.currentTimeMillis());
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(2, months);
		return gc.getTime();
	}

	public static synchronized Date toDayStartDate(String dateStr) {
		String[] list = dateStr.split("-");
		int year = Integer.parseInt(list[0]);
		int month = Integer.parseInt(list[1]);
		int day = Integer.parseInt(list[2]);
		Calendar cale = Calendar.getInstance();
		cale.set(year, month - 1, day, 0, 0, 0);
		return cale.getTime();
	}

	public static synchronized String addTwoScormTime(String scormTime1, String scormTime2) {
		int dotIndex1 = scormTime1.indexOf(".");
		int hh1 = Integer.parseInt(scormTime1.substring(0, 2));
		int mm1 = Integer.parseInt(scormTime1.substring(3, 5));
		int ss1 = 0;
		if (dotIndex1 != -1) {
			ss1 = Integer.parseInt(scormTime1.substring(6, dotIndex1));
		} else {

			ss1 = Integer.parseInt(scormTime1.substring(6, scormTime1.length()));
		}
		int ms1 = 0;
		if (dotIndex1 != -1) {
			ms1 = Integer.parseInt(scormTime1.substring(dotIndex1 + 1, scormTime1.length()));
		}

		int dotIndex2 = scormTime2.indexOf(".");
		int hh2 = Integer.parseInt(scormTime2.substring(0, 2));
		int mm2 = Integer.parseInt(scormTime2.substring(3, 5));
		int ss2 = 0;
		if (dotIndex2 != -1) {
			ss2 = Integer.parseInt(scormTime2.substring(6, dotIndex2));
		} else {

			ss2 = Integer.parseInt(scormTime2.substring(6, scormTime2.length()));
		}
		int ms2 = 0;
		if (dotIndex2 != -1) {
			ms2 = Integer.parseInt(scormTime2.substring(dotIndex2 + 1, scormTime2.length()));
		}

		int hh = 0;
		int mm = 0;
		int ss = 0;
		int ms = 0;

		if (ms1 + ms2 >= 1000) {
			ss = 1;
			ms = ms1 + ms2 - 1000;
		} else {

			ms = ms1 + ms2;
		}
		if (ss1 + ss2 + ss >= 60) {
			mm = 1;
			ss = ss1 + ss2 + ss - 60;
		} else {

			ss = ss1 + ss2 + ss;
		}
		if (mm1 + mm2 + mm >= 60) {
			hh = 1;
			mm = mm1 + mm2 + mm - 60;
		} else {

			mm = mm1 + mm2 + mm;
		}
		hh = hh + hh1 + hh2;

		StringBuffer sb = new StringBuffer();
		if (hh < 10) {
			sb.append("0").append(hh);
		} else {

			sb.append(hh);
		}
		sb.append(":");
		if (mm < 10) {
			sb.append("0").append(mm);
		} else {

			sb.append(mm);
		}
		sb.append(":");
		if (ss < 10) {
			sb.append("0").append(ss);
		} else {

			sb.append(ss);
		}
		sb.append(".");
		if (ms < 10) {
			sb.append(ms).append("00");
		} else if (ms < 100) {
			sb.append(ms).append("0");
		} else {

			sb.append(ms);
		}
		return sb.toString();
	}

	public static int CompareDateWithNow(Date date, int timeType) {
		Date now = Calendar.getInstance().getTime();

		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(now);
		calendarNow.set(11, 0);
		calendarNow.set(12, 0);
		calendarNow.set(13, 0);
		calendarNow.set(14, 0);

		Calendar calendarPara = Calendar.getInstance();
		calendarPara.setTime(date);
		calendarPara.set(11, 0);
		calendarPara.set(12, 0);
		calendarPara.set(13, 0);
		calendarPara.set(14, 0);

//		float nowTime = (float) now.getTime();
//		float dateTime = (float) date.getTime();

		if (timeType == 0) {
			if (calendarNow.get(6) == calendarPara.get(6))
				return 0;
			return (calendarNow.get(1) - calendarPara.get(1)) * 12 + calendarNow.get(2) - calendarPara.get(2);
		}

		int result = 0;
		if (calendarNow.compareTo(calendarPara) == 0)
			return result;
		if (calendarNow.compareTo(calendarPara) > 0) {
			while (calendarNow.compareTo(calendarPara) >= 0) {
				calendarNow.add(6, -1);
				result++;
			}
			return result - 1;
		}
		if (calendarNow.compareTo(calendarPara) < 0) {
			while (calendarNow.compareTo(calendarPara) <= 0) {
				calendarNow.add(6, 1);
				result--;
			}
			return result + 1;
		}
		return 0;
	}

	public static synchronized String toString(Date theDate, DateFormat theDateFormat) {
		if (theDate == null) {
			return "";
		}

		return theDateFormat.format(theDate);
	}

	private static String doTransform(String date) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(date.substring(0, 4));
		buffer.append("-");
		buffer.append(date.substring(4, 6));
		buffer.append("-");
		buffer.append(date.substring(6, 8));
		buffer.append(" ");
		buffer.append(date.substring(8, 10));
		buffer.append(":");
		buffer.append(date.substring(10, 12));
		buffer.append(":");
		buffer.append(date.substring(12, 14));

		return buffer.toString();
	}

	public static int dateToWeekDay(Date date, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (type == 0) {
			return calendar.get(7);
		}
		if (type == 1) {
			if (calendar.get(7) == 1) {
				return 7;
			}
			return calendar.get(7) - 1;
		}

		return -1;
	}

	public static Date getMonthStartDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(new Date(System.currentTimeMillis()));
		gc.set(5, 1);
		return toDayStartDate(df.format(gc.getTime()));
	}

	public static Date getMonthEndDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.add(2, 1);
		cal.set(5, 1);
		cal.add(5, -1);
		return toDayEndDate(df.format(cal.getTime()));
	}

	public static synchronized Date toDayEndDate(String dateStr) {
		String[] list = dateStr.split("-");
		int year = (new Integer(list[0])).intValue();
		int month = (new Integer(list[1])).intValue();
		int day = (new Integer(list[2])).intValue();
		Calendar cale = Calendar.getInstance();
		cale.set(year, month - 1, day, 23, 59, 59);
		return cale.getTime();
	}

	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(5, now.get(5) - day);
		return now.getTime();
	}

	public static Date getYearBefore(Date d, int year) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(1, now.get(1) - year);
		return now.getTime();
	}

	public static Date getMonthBefore(Date d, int month) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(2, now.get(2) - month);
		return now.getTime();
	}

	public static Date parseDateByMilliSecondString(String str) {
		Long millisecond = Long.valueOf(Long.parseLong(str));
		return new Date(millisecond.longValue());
	}

	public static Date getDateAfterSevenDays(Date date) {
		String sdfStrDate = null;
		Date sevenDate = null;
		try {
			sdfStrDate = DateToString(date, "yyyy-MM-dd");
			Date dateOne = parseStringToSpecialDates(sdfStrDate);
			sevenDate = new Date(dateOne.getTime() + 518400000L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sevenDate;
	}

	public static Date getDateAfterSpecialDate(Date date) {
		String sdfStrDate = null;
		Date tomorrowDate = null;
		try {
			sdfStrDate = DateToString(date, "yyyy-MM-dd");
			Date dateOne = parseStringToSpecialDates(sdfStrDate);
			tomorrowDate = new Date(dateOne.getTime() + 86400000L);
			System.out.println(tomorrowDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tomorrowDate;
	}

	public static Date parseStringToSpecialDates(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOne = null;
		try {
			dateOne = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateOne;
	}

	public static long getNow() {
		CurrTime = new Date();
		return CurrTime.getTime();
	}

	public static long getTimebyString(String s) {
		int i = s.indexOf(":");
		int j = s.lastIndexOf(":");
		long time = 0L;
		if (i != -1 && j != -1) {
			int hh = Integer.parseInt(s.substring(0, i));
			int mm = Integer.parseInt(s.substring(i + 1, j));
			int ss = Integer.parseInt(s.substring(j + 1, s.length()));
			time = (hh * 60 * 60 * 1000 + mm * 60 * 1000 + ss * 1000);
		}
		return time;
	}

	public static long getTimesbyString(String s) {
		int i = s.indexOf(":");
		long time = 0L;
		if (i != -1) {
			int hh = Integer.parseInt(s.substring(0, i));
			int mm = Integer.parseInt(s.substring(i + 1, s.length()));
			time = (hh * 60 * 60 * 1000 + mm * 60 * 1000);
		}
		return time;
	}

	public static long getTimeDifference(String begintime, String endtime) {
		long begintimelong = getTimesbyString(begintime);
		long endtimelong = getTimesbyString(endtime);
		if (begintimelong >= endtimelong) {
			return 0L;
		}
		return endtimelong - begintimelong;
	}

	public static String FormatDate(Date date, String string) {
		SimpleDateFormat dateformat = new SimpleDateFormat(string);
		return dateformat.format(date);
	}

	public static String getCurrTime() {
		Date date_time = new Date();
		return FormatDate(date_time, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getYYMMDD() {
		return FormatDate(new Date(), "yyMMdd");
	}

	public static String getCurrMillisTime() {
		long l = System.currentTimeMillis();
		String d = getCurrTime();
		return String.valueOf(d) + ":" + (l - getTimeInMillis(d, "yyyy-MM-dd HH:mm:ss"));
	}

	public static String getCurrShortTime() {
		Date date = new Date();
		return FormatDate(date, "yyyy-MM-dd HH:mm");
	}

	public static String getCurrDate() {
		CurrTime = new Date();
		return FormatDate(CurrTime, "yyyy-MM-dd");
	}

	public static String getYYMMDDHHMMSS() {
		return FormatDate(new Date(), "yyMMddHHmmss");
	}

	public static String getCurrYear() {
		CurrTime = new Date();
		return FormatDate(CurrTime, "yyyy");
	}

	public static String getCurrMonth() {
		CurrTime = new Date();
		return FormatDate(CurrTime, "MM");
	}

	public static String getCurrDay() {
		CurrTime = new Date();
		return FormatDate(CurrTime, "dd");
	}

	public static String getCurrHours() {
		CurrTime = new Date();
		return FormatDate(CurrTime, "HH");
	}

	public static String getCurrMinutes() {
		CurrTime = new Date();
		return FormatDate(CurrTime, "mm");
	}

	public static String getCurrSeconds() {
		CurrTime = new Date();
		return FormatDate(CurrTime, "ss");
	}

	public static String dateToString(Date date) {
		return FormatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String dateToString1(Date date) {
		return FormatDate(date, "yyyy-MM-dd");
	}

	public static String dateToShortString(Date date) {
		return FormatDate(date, "yyyy-MM-dd HH:mm");
	}

	public static int getYearByString(String dateString) {
		int year = 0;
		int i = dateString.indexOf("-");
		int j = dateString.lastIndexOf("-");
		if (i != -1 && j != -1) {
			year = Integer.parseInt(dateString.substring(0, i));
		}
		return year;
	}

	public static int getMonthByString(String dateString) {
		int month = 1;
		int i = dateString.indexOf("-");
		int j = dateString.lastIndexOf("-");
		if (i != -1 && j != -1) {
			month = Integer.parseInt(dateString.substring(i + 1, j));
		}
		return month;
	}

	public static Date stringToDate(String dateString) {
		String sf = "yyyy-MM-dd";
		Date dt = stringToDate(dateString, sf);
		return dt;
	}

	public static Date StringToTime(String dateString) {
		String sf = "yyyy-MM-dd HH:mm:ss";
		Date dt = stringToDate(dateString, sf);
		return dt;
	}

	public static String timeStringChange(String dateString) {
		String sf = "yyyyMMddHHmmss";
		Date dt = stringToDate(dateString, sf);
		String sc = dateToString(dt);
		return sc;
	}

	public static String getPublicDate() {
		Calendar c = Calendar.getInstance();
		int month = c.get(2) + 1;
		String pd = String.valueOf(c.get(1)).substring(2, 4) + ((month > 9) ? month : ("0" + month));
		return pd;
	}

	public static int backyear(int getback) {
		int back_year = 0;
		CurrTime = new Date();
		int nowyear = Integer.parseInt(FormatDate(CurrTime, "yyyy"));
		int nowmonth = Integer.parseInt(FormatDate(CurrTime, "MM"));
		if (nowmonth > getback) {
			back_year = nowyear;
		} else {
			back_year = nowyear - 1;
		}
		return back_year;
	}

	public static int backmonth(int getback) {
		int back_month = 0;
		CurrTime = new Date();
		int nowmonth = Integer.parseInt(FormatDate(CurrTime, "MM"));
		if (nowmonth > getback) {
			back_month = nowmonth - getback;
		} else {
			back_month = nowmonth - getback + 12;
		}
		return back_month;
	}

	public static String dateToyear(Date date, int getback) {
		String dateyear = FormatDate(date, "yyyy");
		String datemonth = FormatDate(date, "MM");
		String newyear = "";
		int oldyear = Integer.parseInt(dateyear);
		int oldmonth = Integer.parseInt(datemonth);
		if (oldmonth > getback) {
			newyear = String.valueOf(oldyear);
		} else {
			newyear = String.valueOf(oldyear - 1);
		}
		return newyear;
	}

	public static String dateTomonth(Date date, int getback) {
		String datemonth = FormatDate(date, "MM");
		String newmonth = "";
		int oldmonth = Integer.parseInt(datemonth);
		if (oldmonth > getback) {
			newmonth = String.valueOf(oldmonth - getback);
		} else {
			newmonth = String.valueOf(oldmonth + 12 - getback);
		}
		return newmonth;
	}

	public static int subtime(Date date_1, Date date_2) {
		String dateyear_1 = FormatDate(date_1, "yyyy");
		String datemonth_1 = FormatDate(date_1, "MM");
		String dateyear_2 = FormatDate(date_2, "yyyy");
		String datemonth_2 = FormatDate(date_2, "MM");
		int year_1 = Integer.parseInt(dateyear_1);
		int year_2 = Integer.parseInt(dateyear_2);
		int month_1 = Integer.parseInt(datemonth_1);
		int month_2 = Integer.parseInt(datemonth_2);
		int subtime = (year_2 - year_1) * 12 + month_2 - month_1 + 1;
		return subtime;
	}

	public static long subsecond(String dateString1, String dateString2) {
		Date date_1 = StringToTime(dateString1);
		Date date_2 = StringToTime(dateString2);
		long subsecond = (date_2.getTime() - date_1.getTime()) / 1000L;
		return subsecond;
	}

	public static String changeFormat(String dateString) {
    int date = Integer.parseInt(dateString);
    int hh = 0;
    int mm = 0;
    int ss = 0;
    hh = date / 3600;
    mm = date % 3600 / 60;
    ss = date % 3600 % 60;
    String changedtime = String.valueOf(hh);
    if (mm != 0) {
      changedtime = String.valueOf(changedtime) + hh;
    }
    if (ss != 0) {
      if (mm == 0) {
        changedtime = String.valueOf(changedtime) + "0";
      }
      changedtime = String.valueOf(changedtime) + ss;
    } 
    return changedtime;
  }

	public static long changeSecondToDay(long timelength) {
		long day = 0L;
		if (timelength % 86400L == 0L) {
			day = timelength / 24L / 3600L;
		} else {
			day = timelength / 24L / 3600L + 1L;
		}
		return day;
	}

	public static String changeSecondToMinute(String timelengthString) {
    long minute = 0L;
    String minuteString = "";
    long timelength = Long.parseLong(timelengthString);
    if (timelength % 60L == 0L) {
      minute = timelength / 60L;
    } else {
      minute = timelength / 60L + 1L;
    } 
    minuteString = String.valueOf(Long.toString(minute));
    return minuteString;
  }

	public static String changeDayToMinute(String dayString) {
    long minute = 0L;
    long day = Long.parseLong(dayString);
    String minuteString = "";
    minute = day * 24L * 60L;
    minuteString = String.valueOf(Long.toString(minute));
    return minuteString;
  }

	public static String changeMinuteToHour(String minuteString) {
    long minute = Long.parseLong(minuteString);
    long hour = 0L;
    String hourString = "";
    if (minute % 60L == 0L) {
      hour = minute / 60L;
      hourString = String.valueOf(Long.toString(hour));
    } else {
      hour = minute;
      hourString = String.valueOf(Long.toString(hour));
    } 
    return hourString;
  }

	public static Date stringToDate(String dateString, String sf) {
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf = new SimpleDateFormat(sf);
		Date dt = sdf.parse(dateString, pos);
		return dt;
	}

	/**
	 * 获取年月
	 * @return
	 */
	public static String getMonthStr() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) + String.valueOf(cal.get(Calendar.MONTH)+1);
	}

	/**
	 * 获取年月日
	 * @return
	 */
	public static String getNowDayStr() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) + String.valueOf(cal.get(Calendar.MONTH)+1) + cal.get(Calendar.DATE);
	}

	/**
	 * 返回年月日时分秒
	 * @return
	 */
	public static String getNowTimeStr() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) + String.valueOf(cal.get(Calendar.MONTH)+1) + cal.get(Calendar.DATE) +cal.get(Calendar.HOUR) + cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND);
	}

	public static String changeOracleDate(String date) {
		if (date.indexOf(".") != -1) {
			return date.substring(0, date.indexOf("."));
		}
		return date;
	}

	public static String getBeginTimeEqualsEndTimeFlag(String beginTime, String endTime) {
		Date currentDate = new Date();
		Date hangBeginTime = stringToDate(beginTime);
		Date hangEndTime = stringToDate(endTime);
		String flag = "";

		if (hangBeginTime.before(hangEndTime)) {
			if (hangBeginTime.before(currentDate) && currentDate.before(hangEndTime)) {

				flag = "1";
			} else if (hangEndTime.before(currentDate)) {
				flag = "2";
			} else if (currentDate.before(hangBeginTime)) {
				flag = "0";
			}
		}
		return flag;
	}

	public static String getCurrTimes() {
		Date date_time = new Date();
		return FormatDate(date_time, "HH:mm:ss");
	}

	public static int GetWeekDay(String TempDate, String format) {
		int temp = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(TempDate));
			temp = c.get(7);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static String getTimeToAddDate(String time, String format, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(time, format));

		calendar.add(5, date);

		return FormatDate(calendar.getTime(), format);
	}

	public static String getTimeInMillisToAdd(String time, String format, long timeInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(time, format));

		calendar.setTimeInMillis(calendar.getTimeInMillis() + timeInMillis);
		return dateToString(calendar.getTime());
	}

	public static long getTimeInMillis(String time, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(time, format));

		return calendar.getTimeInMillis();
	}

	public static int getWeekDays(String beginDay, String endDay) {
		int k = 0;
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTimeZone(TimeZone.getDefault());
		calBegin.clear();
		calBegin.setTime(stringToDate(beginDay));

		Calendar calEnd = Calendar.getInstance();
		calEnd.setTimeZone(TimeZone.getDefault());
		calEnd.clear();
		calEnd.setTime(stringToDate(endDay));

		while (calBegin.before(calEnd)) {

			if (calBegin.get(7) == 1 || calBegin.get(7) == 7) {
				k++;
			}
			calBegin.roll(5, true);

			if (calBegin.after(calEnd)) {
				break;
			}
		}

		if (GetWeekDay(endDay, "yyyy-MM-dd") == 1 || GetWeekDay(endDay, "yyyy-MM-dd") == 7) {
			k++;
		}
		return k;
	}

	public static int getDayOfWeek(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
		cal.clear();
		cal.set(year, month - 1, date);
		return cal.get(7);
	}

	public static int getLastDayOfMonth(String time, String format) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(stringToDate(time, format));
		int lastDay = cDay1.getActualMaximum(5);
		return lastDay;
	}

	public static String getTimeOfInform() {
		String time = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(CurrTime);
		int lastDay = calendar.getActualMaximum(5);
		int day = calendar.get(5);
		int week = calendar.get(7);
		int year = calendar.get(1);
		int month = calendar.get(2) + 1;
		if (week - 7 == 0) {
			if (day + 4 - lastDay > 0) {
				month++;
				day = day + 4 - lastDay;
			} else {
				day += 4;
			}
		} else if (week - 4 >= 0) {
			if (day + 7 - week - lastDay > 0) {
				month++;
				day = day + 7 - week - lastDay;
			} else {
				day = day + 7 - week;
			}

		} else if (day + 4 - week - lastDay > 0) {
			month++;
			day = day + 4 - week - lastDay;
		} else {
			day = day + 4 - week;
		}

		String m = null;
		String d = null;
		if (month <= 9) {
			m = "0" + month;
		} else {
			m = (new Integer(month)).toString();
		}
		if (day <= 9) {
			d = "0" + day;
		} else {
			d = (new Integer(day)).toString();
		}
		time = String.valueOf(year) + "-" + m + "-" + d + " 22:00:00";

		return time;
	}

	public static int getMonthEqualsDays(String begintime, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(begintime));
		calendar.add(2, month);

		return (int) ((calendar.getTimeInMillis() - stringToDate(begintime).getTime()) / 1000L / 24L / 60L / 60L);
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = new String[0];
		if (value instanceof Date[]) {
			SimpleDateFormat sf = new SimpleDateFormat(this.format);
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		if (value instanceof Date) {
			String str = (new SimpleDateFormat(this.format)).format((Date) value);
			return str;
		}
		return (value == null) ? null : value.toString();
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public static long getMaxTime(String year, String month) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(1, (new Integer(year)).intValue());
		startCalendar.set(2, (new Integer(month)).intValue() - 1);
		startCalendar.set(5, startCalendar.getActualMaximum(5));
		startCalendar.set(11, startCalendar.getActualMaximum(11));
		startCalendar.set(12, startCalendar.getActualMaximum(12));
		startCalendar.set(13, startCalendar.getActualMaximum(13));
		startCalendar.set(14, startCalendar.getActualMaximum(14));
		return startCalendar.getTimeInMillis();
	}

	public static String getLongTimeByTime(long time, String formatStr) {
		String returnStr = "";
		if (time > 0L) {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			return format.format(new Date(time));
		}
		return returnStr;
	}
}
