package com.dsys.common.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @discription 时间工具类
 * @author shilp
 * @created 2020/6/17  16:18
 * @Param
 * @Return
*/
public class DateUtils{
	
	public static final String DATE_FMT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public DateUtils() {
	}
	
	public static String FormatDate(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FMT_PATTERN);
		String strDateTime = formatter.format(localDateTime);
		return strDateTime;
	}
	
	public static String DateToStr(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String strDateTime = formatter.format(localDateTime);
		return strDateTime;
	}

	public static String DateToStrYear(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		String strDateTime = formatter.format(localDateTime);
		return strDateTime;
	}

	public static String DateToStr(LocalDateTime localDateTime, String pattern) {
		String strDateTime = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		strDateTime = formatter.format(localDateTime);
		return strDateTime;
	}
	
	public static String FormatDate(LocalDateTime localDateTime,String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String strDateTime = formatter.format(localDateTime);
		return strDateTime;
	}

	public static LocalDate YmdToDate(int year, int month, int day) {
		LocalDate localDate = LocalDate.of(year, month, day);
		return localDate;
	}

	public static LocalDateTime StrToDateTime(String str) {
		LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern(DATE_FMT_PATTERN));
		return localDateTime;
	}
	
	
	public static LocalTime StrToTime(String str) {
		LocalTime localDateTime = LocalTime.parse(str, DateTimeFormatter.ofPattern(DATE_FMT_PATTERN));
		return localDateTime;
	}
	
	public static LocalDate StrToDate(String str) {
		LocalDate localDateTime = LocalDate.parse(str, DateTimeFormatter.ofPattern(DATE_FMT_PATTERN));
		return localDateTime;
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

	public static Date getDateAfterSevenDays(LocalDateTime date) {
		String sdfStrDate = null;
		Date sevenDate = null;
		try {
			sdfStrDate = DateToStr(date, "yyyy-MM-dd");
			Date dateOne = parseStringToSpecialDates(sdfStrDate);
			sevenDate = new Date(dateOne.getTime() + 518400000L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sevenDate;
	}

	public static Date getDateAfterSpecialDate(LocalDateTime date) {
		String sdfStrDate = null;
		Date tomorrowDate = null;
		try {
			sdfStrDate = DateToStr(date, "yyyy-MM-dd");
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

	

	public static String getCurrTime() {
		return FormatDate(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getYYMMDD() {
		return FormatDate(LocalDateTime.now(), "yyMMdd");
	}

	public static String getCurrMillisTime() {
		long l = System.currentTimeMillis();
		String d = getCurrTime();
		return String.valueOf(d) + ":" + (l - getTimeInMillis(d, "yyyy-MM-dd HH:mm:ss"));
	}

	public static String getCurrShortTime() {
		return FormatDate(LocalDateTime.now(), "yyyy-MM-dd HH:mm");
	}

	
	public static String getYYMMDDHHMMSS() {
		return FormatDate(LocalDateTime.now(), "yyMMddHHmmss");
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

	public static String getPublicDate() {
		Calendar c = Calendar.getInstance();
		int month = c.get(2) + 1;
		String pd = String.valueOf(c.get(1)).substring(2, 4) + ((month > 9) ? month : ("0" + month));
		return pd;
	}


	public static Date stringToDate(String dateString, String sf) {
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf = new SimpleDateFormat(sf);
		Date dt = sdf.parse(dateString, pos);
		return dt;
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

	public static int getMonthEqualsDays(String begintime, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.clear();
		calendar.setTime(stringToDate(begintime));
		calendar.add(2, month);

		return (int) ((calendar.getTimeInMillis() - stringToDate(begintime).getTime()) / 1000L / 24L / 60L / 60L);
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
