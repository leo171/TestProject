package cn.stock.service;

import java.util.ArrayList;
import java.util.List;

import cn.stock.common.util.DateUtil;
import cn.stock.entity.TDXDailyData;
import cn.stock.parster.TDXPaster;
import cn.stock.util.TDXFileUtil;

public class TDXDailyDataService {
	TDXPaster tdxPaster = new TDXPaster();
	
	/**
	 * 获取日线数据
	 * @param stockCode 股票代码
	 * @param startDate 起始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<TDXDailyData> getDailyByDateRange(String stockCode,String startDate,String endDate){
		List<TDXDailyData> datas = new ArrayList<TDXDailyData>();
		String dayFile = TDXFileUtil.getStrTDXFile(stockCode);
		datas = tdxPaster.getDataByDays(dayFile, startDate, endDate);
		return datas;
	}
	
	/**
	 * 返回一个Json格式的字符串数据
	 * @param stockCode 股票代码
	 * @param startDate 起始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public String getJsonByDateRange(String stockCode,String startDate,String endDate){
		List<TDXDailyData> dataList = getDailyByDateRange(stockCode, startDate, endDate);
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0;i<dataList.size();i++){
			if(i == dataList.size() -1){
				sBuffer.append(dataList.get(i).toString());
			}else{
				sBuffer.append(dataList.get(i).toString()).append("|");
			}
		}
		return sBuffer.toString();
	}
	
	/**
	 * 获取某股票某一天的日线数据(如果找不到当前日期，找最近一日的日线数据)
	 * @param stockCode
	 * @param date
	 * @return
	 */
	public TDXDailyData getDailyByDate1(String stockCode,String date){
		String formDate = DateUtil.addDateToString(date, -50);
		List<TDXDailyData> data = getDailyByDateRange(stockCode, formDate, date);
		if(data.size()>0){
			return data.get(data.size()-1);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取某股票某一天的日线数据(如果找不到当前日期，返回null)
	 * @param stockCode
	 * @param date
	 * @return
	 */
	public TDXDailyData getDailyByDate2(String stockCode,String date){
		List<TDXDailyData> data = getDailyByDateRange(stockCode, date, date);
		if(data.size()>0){
			return data.get(0);
		}else{
			return null;
		}
	}
}
