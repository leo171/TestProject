package cn.stock.util;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.stock.common.helper.ConfigHelper;
import cn.stock.common.util.DateUtil;
import cn.stock.common.util.FileUtil;
import cn.stock.entity.TDXDailyData;
/**
 * 股票日期相关的工具类，内置一个已经计算好股票交易日的集合
 * @author Administrator
 *
 */
public class StockDateUtil extends DateUtil{
	
	public static List<String> WorkingDateList = new ArrayList<String>();
	/** 中国股市首开市日 */
	private static String startDate = "19900101";//19900101
	/** 中国股市80周岁	 */
	private static int dateCount = 365 * 80;
	
	static{
		String propStartDate = ConfigHelper.getDefKey("STOCKDATEHELPER_START_DATE");
		if(propStartDate.length()>0){
			startDate = propStartDate;
		}
		String propDateCount = ConfigHelper.getDefKey("STOCKDATEHELPER_COUNT_YEARS");
		if(propDateCount.length()>0){
			dateCount = 365 * Integer.parseInt(propDateCount);
		}
		for(int i = 0;i<= dateCount;i++){
			String currDate = DateUtil.addDateToString(startDate,i);
			if(isNormalWorkingDate(currDate)){
				WorkingDateList.add(currDate);
			}
		}
		File file = new File(ConfigHelper.getDefKey("STOCKDATEHELPER_HOLIDAY_FILE"));
		if(file.exists()){
			List<String> tempHDayFromFile = FileUtil.readFileByLines(file);
			for(String date : tempHDayFromFile){
				if(isNormalWorkingDate(date)){
					int index = findDate(WorkingDateList, date);
					if(index!=-1){
						WorkingDateList.remove(index);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws ParseException {
//		System.out.println(StockDateUtil.WorkingDateList.size());
		System.out.println(getNowWorkingDate());
	}
	
	/**
	 * 使用二分法在日期集合中找出对应的日期  
	 * @param dateList 日期集合
	 * @param dateStr 日期字符串  格式:yyyyMMdd
	 * @return 返回对应的索引，找不到返加-1
	 */
	public static int findDate(List<String> dateList, String dateStr) {
		int start = 0;
		int end = dateList.size() - 1;
		int date = Integer.parseInt(dateStr);
		while (start <= end) {
			int middle = (start + end) / 2;
			if (date < Integer.parseInt(dateList.get(middle))) {
				end = middle - 1;
			} else if (date > Integer.parseInt(dateList.get(middle))) {
				start = middle + 1;
			} else {
				return middle;
			}
		}
		return -1;
	}
	
	/**
	 * 使用二分法在日期集合中找出对应的日期  
	 * @param dateList 日线数据集合
	 * @param dateStr 日期字符串  格式:yyyyMMdd
	 * @return 返回对应的索引，找不到返加-1
	 */
	public static int findDate(String dateStr, List<TDXDailyData> dateList) {
		int start = 0;
		int end = dateList.size() - 1;
		int date = Integer.parseInt(dateStr);
		while (start <= end) {
			int middle = (start + end) / 2;
			if (date < Integer.parseInt(dateList.get(middle).getDate())) {
				end = middle - 1;
			} else if (date > Integer.parseInt(dateList.get(middle).getDate())) {
				start = middle + 1;
			} else {
				return middle;
			}
		}
		return -1;
	}
	
	
	/**
	 * 以referenceDate为参考日期，定位到dayCount日后的某个股票交易日
	 * @param referenceDate 参考日期起始日
	 * @param dayCount 跳转的日数，可为负数
	 * @return
	 */
	public static String locateTransactionDate(String referenceDate, int dayCount){
		if(!DateUtil.checkIsYYYYMMDDFormat(referenceDate)){
			 throw new RuntimeException("日期格式无效");
		}
		int index = findDate(WorkingDateList, referenceDate);
		if(index == -1){
			throw new RuntimeException("找不到对应日期");
		}
		index += dayCount;
		if(index < 0 || index > WorkingDateList.size() -1){
			throw new RuntimeException("查找日期超出范围");
		}
		return WorkingDateList.get(index);
	}
	
	/**
	 * 获取当前最近一日的交易日(如果当天不是交易日，取之前的最近一日有效交易日)
	 * @return
	 */
	public static String getNowWorkingDate(){
		String currDate = sdf.format(new Date());
		while(!isNormalWorkingDate(currDate)){
			currDate = addDateToString(currDate, -1);
		}
		return currDate;
	}
}
