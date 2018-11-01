package com.aotain.common.utils.impala;

import com.aotain.common.utils.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImpalaUtil {

	private static Logger logger = LoggerFactory.getLogger(ImpalaUtil.class);

	/**
	 * 根据统计周期来获取对应的日期值，目前hive表中只有分钟和小时粒度的表日期字段类型为时间戳，日，周，月粒度都为日期值，
	 * 类型为整型，如2015-10-10在数据库中的值为20151010
	 * @param statType 
	 * @param timeStr
	 * @return
	 */
	public static long getTimestampByStatType(int statType,String timeStr){
		DateTimeFormatter df = getDateTimeFormatByStatType(statType,null);
		DateTime time = DateTime.parse(timeStr,df);
		if(statType > 2){
			if(statType == 4){
				time = time.minusDays(time.dayOfWeek().get()-1);
			}
			df = DateTimeFormat.forPattern("yyyyMMdd");
			return Long.valueOf(time.toString(df));
		}
		return time.getMillis()/1000;
	}
	
	public static long getTimestamp(String timeStr){
		DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime time = DateTime.parse(timeStr,df);
		return time.getMillis()/1000;
	}
	/**\
	 * 根据统计周期来获取对应的日期值，当hive表中日期字段类型为时间戳
	 * @param timeStr 时间
	 * @param statType 时间类型
	 * @param defaullFormate 默认的时间格式
	 * @return
	 */
	public static long getTimestampBaseFormat(String timeStr,int statType,String defaullFormate){
		DateTimeFormatter df = getDateTimeFormatByStatType(statType, defaullFormate);
		DateTime time = DateTime.parse(timeStr,df);
		return time.getMillis()/1000;
	}
	
	/**
	 * 时间戳转日期
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String timestampToDataString(long time, String pattern){
		Date date = new Date(time*1000l);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
	}
	
	public static String timestampToDataString(long time,int statType){
		return  timestampToDataString(time,getDatePatternByStatType(statType,null));
	}
	
	/**
	 * 当类型为统计时，根据统计周期获取对应分区，默认只针对天分区
	 * @param statType 统计粒度包括 分钟，小时，天，周，月
	 * @param time
	 * @param format
	 * @return
	 */
	public static String getColumnPartDate(int statType,String time,String format){
		if(!StringUtils.isBlank(time)){
			DateTimeFormatter df = getDateTimeFormatByStatType(statType,format);
			DateTime startTime = DateTime.parse(time,df);
			switch (statType) {
			case 4:
				startTime = startTime.minusDays(startTime.dayOfWeek().get()-1);
				break;
			default:
				break;
			}
			StringBuilder partdate = new StringBuilder();		
			partdate.append(DateUtils.getYearMonthDay(startTime));
			return partdate.toString();
		}
		return null;
	}
	/**
	 * 当类型为统计时，根据统计周期获取对应分区，默认只针对天分区
	 * @param statType 统计粒度包括 天，周，月
	 * @param time
	 * @param format
	 * @return
	 */
	public static String getColumnPartDateByYMD(int statType,String time,String format){
		if(!StringUtils.isBlank(time)){
			DateTimeFormatter df = getDateTimeFormatByStatType(statType,format);
			DateTime startTime = DateTime.parse(time,df);
			switch (statType) {
				case 4:
					startTime = startTime.minusDays(startTime.dayOfWeek().get()-1);
					break;
				default:
					break;
			}
			StringBuilder partdate = new StringBuilder();
			partdate.append(DateUtils.getYearMonthDay(startTime));
			return partdate.toString();
		}
		return null;
	}
	
	/**
	 * 当类型为趋势统计时，根据开始时间和结束时间来计算分区，默认只针对天分区
	 * @param statType 
	 * @param startTime
	 * @param endTime
	 * @param defaultformat
	 * @return
	 */
	public static String getPartDate(int statType,String startTime,String endTime,String defaultformat){
		if(!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)){
			DateTimeFormatter df = getDateTimeFormatByStatType(statType,defaultformat);
			DateTime starttime = DateTime.parse(startTime,df);
			DateTime endtime = DateTime.parse(endTime,df);
			int dayDiff = Days.daysBetween(starttime, endtime).getDays();
			StringBuilder partdate = new StringBuilder();		
			for(int i = 0; i <= dayDiff; i++){ 
				DateTime iTime = endtime.minusDays(i);
				partdate.append(",").append(DateUtils.getYearMonthDay(iTime));
			}
			if(!StringUtils.isEmpty(partdate)){
				return partdate.substring(1);
			}
		}
		return null;
	}
	
	/**
	 * 根据统计周期获取格式化日期
	 * @param statType
	 * @param defaultformat
	 * @return
	 */
	private static DateTimeFormatter getDateTimeFormatByStatType( int statType, String defaultformat){
		DateTimeFormatter df = null;
		switch (statType) {
		case 1:
			df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
			break;
		case 2:
			df = DateTimeFormat.forPattern("yyyy-MM-dd HH");
			break;
		case 3:
			df = DateTimeFormat.forPattern("yyyy-MM-dd");
			break;
		case 4:
			df = DateTimeFormat.forPattern("yyyy-MM-dd");
			break;
		case 5:
			df = DateTimeFormat.forPattern("yyyy-MM");
			break;
		default:
			df = DateTimeFormat.forPattern(defaultformat);
			break;
		}
		return df;
	}
	
	/**
	 * 根据统计周期，开始时间和结束时间获取对应的分区,分钟，小时，日获取天的分区，周，月获取所在周，月的分区
	 * @param statType
	 * @param startTime
	 * @param endTime
	 * @param defaultformat
	 * @return
	 */
	public static String getPartDateByStatType(int statType,String startTime,String endTime,String defaultformat){
		if(statType < 4){
			return getPartDate(statType,startTime,endTime,defaultformat);
		}
		DateTimeFormatter df = getDateTimeFormatByStatType(statType,defaultformat);
		DateTime starttime = DateTime.parse(startTime,df);
		DateTime endtime = DateTime.parse(endTime,df);
		StringBuilder partdate = new StringBuilder();
		switch (statType) {
		case 4:
			starttime = starttime.minusDays(starttime.dayOfWeek().get()-1);
			endtime = endtime.minusDays(endtime.dayOfWeek().get()-1);
			int weeksDiff = Weeks.weeksBetween(starttime, endtime).getWeeks();
			for(int i = 0; i <= weeksDiff; i++){ 
				DateTime iTime = endtime.minusWeeks(i);
				partdate.append(",").append(DateUtils.getYearMonthDay(iTime));
			}
			break;
		case 5:
			int monthsDiff = Months.monthsBetween(starttime, endtime).getMonths();
			for(int i = 0; i <= monthsDiff; i++){ 
				DateTime iTime = endtime.minusMonths(i);
				partdate.append(",").append(DateUtils.getYearMonthDay(iTime));
			}
			break;
		default:
			break;
		}
		if(!StringUtils.isEmpty(partdate)){
			return partdate.substring(1);
		}
		return null;
	}

	/**
	 * change yyyy-MM-dd to yyyyMMdd
	 * @param time
	 * @return
	 */
	public static String turnPattern(String time){
		SimpleDateFormat format = new SimpleDateFormat(getDatePatternByStatType(3,""));
		try {
			Date date =format.parse(time);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			return simpleDateFormat.format(date);
		} catch (ParseException e) {
			logger.error("change date format error",e);
			return null;
		}
	}
	
	private static String getDatePatternByStatType(int statType,String defaultformat){
		String pattern = null;
		switch (statType) {
		case 1:
			pattern = "yyyy-MM-dd HH:mm";
			break;
		case 2:
			pattern = "yyyy-MM-dd HH";
			break;
		case 3:
			pattern = "yyyy-MM-dd";
			break;
		case 4:
			pattern = "yyyy-MM-dd";
			break;
		case 5:
			pattern = "yyyy-MM";
			break;
		default:
			pattern = defaultformat;
			break;
		}
		return pattern;
	}
	
	public static boolean statTypeCheck(int statType){
		//return statType < 3 ? false:true;
		return statType <= 2 ? true:false;
	}



	
	public static void main(String[] args) {
		String result = timestampToDataString(1495414800,3);//1495209600 1495414800 1495382400 1495414800 1495382400
//		System.out.println("result is " + result);
		long l = getTimestampBaseFormat("2017-05-22",3,"yyyy-mm-dd");
		System.out.println("result is " + l);
	}
	
}
