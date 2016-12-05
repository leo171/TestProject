package cn.stock.date_report;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.RecalcIdRecord;

import cn.stock.common.helper.ConfigHelper;
import cn.stock.common.util.DateUtil;
import cn.stock.common.util.TextUtil;
import cn.stock.entity.TDXDailyData;
import cn.stock.service.TDXDailyDataService;
import cn.stock.util.StockDateUtil;

public class DateReportService {
	private List<DateRptCfgBean> cfgBeanList = new ArrayList<DateRptCfgBean>();
	
	private static final int MAX_DAYS = 89;//算上当天，所以要-1
	
	private TDXDailyDataService tdxDailyDataService = new TDXDailyDataService();
	
	public Logger logger;
	
	public DateReportService(){
		logger = Logger.getLogger(this.getClass().getName());
		DateRptCfgLoader cfgLoader = new DateRptCfgLoader();
		cfgBeanList = cfgLoader.getConfig();
	}
	
	public List<DateReportBean> handleDatas(){
		DateRptCfgBean baseCfgBean = cfgBeanList.get(0);
		List<DateReportBean> reportRecordList = new ArrayList<DateReportBean>();
		DateRptCfgBean tempCfgBean = null;
		String today = DateUtil.getNowDate();
		for(int i = 1;i<cfgBeanList.size();i++){
			int absDaySub = MAX_DAYS;
			
			tempCfgBean = cfgBeanList.get(i);
			System.out.println("---------" + tempCfgBean.getStockCode());
			
			DateReportBean reportRecord = new DateReportBean();
			
			
			if(tempCfgBean.getMarkDate()==null || tempCfgBean.getMarkDate().trim().length()==0){
				System.out.println(tempCfgBean.getStockCode() + "没有输入日期，跳过该记录。");
				continue;
			}
			
			
			List<TDXDailyData> dailyDataList = tdxDailyDataService.getDailyByDateRange(tempCfgBean.getStockCode(), tempCfgBean.getMarkDate(),  today);
			
			String endDate = dailyDataList.get(dailyDataList.size()-1).getDate();
			
			int betweenDays = DateUtil.daysBetween(endDate, today, "yyyyMMdd");
			
			
			absDaySub =betweenDays;
			
			
			
			
//			if(dailyDataList.size() < MAX_DAYS){
//				endDate = StockDateUtil.locateTransactionDate(endDate, MAX_DAYS-dailyDataList.size());
//			}
			
//			List<TDXDailyData> dailyDataList = tdxDailyDataService.getDailyByDateRange(tempCfgBean.getStockCode(), tempCfgBean.getMarkDate(), endDate);
			
			
			
			
			System.out.println(dailyDataList.size());
			
//			int indexOfCurrWorkDate = StockDateUtil.findDate(StockDateUtil.getNowWorkingDate(), dailyDataList);
					
			int maxIndexOfList = dailyDataList.size()-1;// 最新一天索引
			
			reportRecord.setStockCode(tempCfgBean.getStockCode()); 	// 股票代码
			reportRecord.setStockName(tempCfgBean.getStockName()); 	// 股票日期
			reportRecord.setMarkDate(tempCfgBean.getMarkDate());	// 标记日期
			reportRecord.setMarkClosePrice(dailyDataList.get(0).getClose());	//标记收盘价
			
			
			// 当天偏离值 (区间的最高/低值)
			double lowest = dailyDataList.get(0).getLow();
			double low2 = dailyDataList.get(maxIndexOfList).getLow();
			if(lowest>low2){
				lowest = low2;
			}
			
			double highest = dailyDataList.get(0).getHigh();
			double high2 = dailyDataList.get(maxIndexOfList).getHigh();
			if(highest<high2){
				highest = high2;
			}
			
			reportRecord.setAbsPriceOfToday(highest-lowest); 		
			
			// 离5日相差天数 ---------------------------------------------
			//TODO 当前日期超过标记的日期范围时
			//TODO 当前日期在标记日期之前
			 
			
			
			
//			int markDateIndex = StockDateUtil.findDate(StockDateUtil.WorkingDateList, tempCfgBean.getMarkDate());
//			
//			String currWorkingDate = StockDateUtil.getNowWorkingDate(); 
//			System.out.println("curr date- " + currWorkingDate);
//			System.out.println("calc date" + StockDateUtil.locateTransactionDate("20160711", 89));
//			int currWorkingDateIndex = StockDateUtil.findDate(StockDateUtil.WorkingDateList,currWorkingDate);
//			
//			System.out.println("start-end" + StockDateUtil.WorkingDateList.get(6906) + "," + StockDateUtil.WorkingDateList.get(6994));
			
//			for(int j = 6906;j<=6994;j++){
//				System.out.println(StockDateUtil.WorkingDateList.get(j));
//			}
//			System.exit(0);
			
			
			//     5 10 (6) 16
			
			
			
			TDXDailyData markTDXDailyData = dailyDataList.get(0); //标记的daily data
			
			TDXDailyData tempDailyData = null;
			
			int warningDays= 5-dailyDataList.size()-absDaySub; //相减后的index
			double result = 0; 
			
			reportRecord.setDayOfWarning5(warningDays);
			if(5<=dailyDataList.size()){
				tempDailyData = dailyDataList.get(5 - 1); //第5日的daily data
				result = (tempDailyData.getClose() / markTDXDailyData.getOpen())-1; 
				reportRecord.setDayOfWarning5ClosePrice(result);
				System.out.println(tempDailyData.getDate() + "," + warningDays);
			}

			
			// 离8日相差天数 ---------------------------------------------
			
			warningDays= 8-dailyDataList.size()-absDaySub; //相减后的index
			reportRecord.setDayOfWarning8(warningDays);
			if(8<=dailyDataList.size()){
				tempDailyData = dailyDataList.get(8 - 1); //第5日的daily data
				result = (tempDailyData.getClose() / markTDXDailyData.getOpen())-1; 
				reportRecord.setDayOfWarning8ClosePrice(result);
				System.out.println(tempDailyData.getDate() + "," + warningDays);
			}
			
			// 离13日相差天数 ---------------------------------------------
			
			warningDays= 13-dailyDataList.size()-absDaySub; //相减后的index
			reportRecord.setDayOfWarning13(warningDays);
			if(13<=dailyDataList.size()){
				tempDailyData = dailyDataList.get(13 - 1); //第5日的daily data
				result = (tempDailyData.getClose() - markTDXDailyData.getOpen())/ markTDXDailyData.getOpen(); 
				reportRecord.setDayOfWarning13ClosePrice(result);
				System.out.println(tempDailyData.getDate() + "," + warningDays);
			}
			
			
			// 离21日相差天数 ---------------------------------------------
			
			warningDays= 21-dailyDataList.size()-absDaySub; //相减后的index
			System.out.println(warningDays);
			if(21<=dailyDataList.size()){
				tempDailyData = dailyDataList.get(21 - 1); //第5日的daily data
				result = (tempDailyData.getClose() - markTDXDailyData.getOpen())/ markTDXDailyData.getOpen(); 
				reportRecord.setDayOfWarning21ClosePrice(result);
				System.out.println(tempDailyData.getDate());
			}
			reportRecord.setDayOfWarning21(warningDays);
			
			
			
			// 离34日相差天数 ---------------------------------------------
			
			warningDays= 34-dailyDataList.size()-absDaySub; //相减后的index
			if(34<=dailyDataList.size()){
				tempDailyData = dailyDataList.get(34 - 1); //第5日的daily data
				result = (tempDailyData.getClose() - markTDXDailyData.getOpen())/ markTDXDailyData.getOpen(); 
				reportRecord.setDayOfWarning34ClosePrice(result);
				System.out.println(tempDailyData.getDate());
			}
			reportRecord.setDayOfWarning34(warningDays);
			
			// 离55日相差天数 ---------------------------------------------
			
			warningDays= 55-dailyDataList.size()-absDaySub; //相减后的index
			System.out.println(warningDays);
			if(55<=dailyDataList.size()){
				tempDailyData = dailyDataList.get(55 - 1); //第5日的daily data
				result = (tempDailyData.getClose() - markTDXDailyData.getOpen())/ markTDXDailyData.getOpen(); 
				reportRecord.setDayOfWarning55ClosePrice(result);
				System.out.println(tempDailyData.getDate());
			}
			reportRecord.setDayOfWarning55(warningDays);
			
			// 离89日相差天数 ---------------------------------------------
			
			warningDays= 89-dailyDataList.size()-absDaySub; //相减后的index
			System.out.println(warningDays);
			if(89<=dailyDataList.size()){
				tempDailyData = dailyDataList.get(89 - 1); //第5日的daily data
				result = (tempDailyData.getClose() - markTDXDailyData.getOpen())/ markTDXDailyData.getOpen(); 
				reportRecord.setDayOfWarning89ClosePrice(result);
				System.out.println(tempDailyData.getDate());
			}
			reportRecord.setDayOfWarning89(warningDays);
			
			// 预置突破价格
			TDXDailyData currentTDXDailyData = dailyDataList.get(maxIndexOfList);
			
			String [] tempValue=TextUtil.nullValueConvert(tempCfgBean.getPriceWarningArea()).split(",");
			
			double[] area = new double[tempValue.length];
			for(int j = 0;j<tempValue.length;j++){
				if(tempValue[j].length()==0){
					break;
				}
				area[j] = Double.parseDouble(tempValue[j]);
			}
			
			reportRecord.setPriceWarningArea(tempCfgBean.getPriceWarningArea());
			
			if(area.length==1 && area[0]>0 && currentTDXDailyData.getClose()>=area[0]){
				reportRecord.setPriceWarningAreaFlag(1);
			}else if(area.length==1 && area[0]<0 && currentTDXDailyData.getClose()<=Math.abs(area[0])){
				reportRecord.setPriceWarningAreaFlag(1);	
			}else if(area.length==2 && area[0] < area[1] && (currentTDXDailyData.getClose()<=area[0] || currentTDXDailyData.getClose()>=area[1])){
				reportRecord.setPriceWarningAreaFlag(1);
			}
				
//			if(area.length==1 ){
//				if(area[0]>0 && currentTDXDailyData.getClose()>=area[0]){
//					
//				}else if(area[0]<0 && currentTDXDailyData.getClose()<=Math.abs(area[0])){
//					
//				}
//			}else if(area.length==2){
//				if(area[0] < area[1] && (currentTDXDailyData.getClose()<=area[0] || currentTDXDailyData.getClose()>=area[0])){
//					
//				}
//			}
			
			//min vol 最小成交量 最低价格 及 箱体价格
			long minVol = currentTDXDailyData.getVol();
			
			reportRecord.setWarningMinVol(1); //最小成交量计算
			
			double maxPrice = dailyDataList.get(0).getHigh();
			double minPrice = dailyDataList.get(0).getLow();
			
			for(TDXDailyData dailyData : dailyDataList){
				if(dailyData.getVol()<minVol && reportRecord.getWarningMinVol()==1){
					reportRecord.setWarningMinVol(0);
				}
				if(maxPrice < dailyData.getHigh()){
					maxPrice = dailyData.getHigh();
				}
				if(minPrice > dailyData.getLow()){
					minPrice = dailyData.getLow();
				}
			}
			
			if(currentTDXDailyData.getLow()== minPrice){
				reportRecord.setWarningMinPrice(minPrice);
			}
			
			reportRecord.setBoxTopPrice(maxPrice);
			
			reportRecord.setBox3_4Price(minPrice+(maxPrice-minPrice)*0.75);
			
			reportRecord.setBoxMiddlePrice(minPrice+(maxPrice-minPrice)*0.5);
			
			reportRecord.setBox1_4Price(minPrice+(maxPrice-minPrice)*0.25);
			
			reportRecord.setBoxLowPrice(minPrice);
			
			reportRecord.setCurrentPriceInBox((currentTDXDailyData.getClose()-minPrice)/(maxPrice-minPrice));
			
			// 自定义价格提醒
			double comparePrice = baseCfgBean.getDiyPrice1();
			if(baseCfgBean.getDiyPrice1()>0 & currentTDXDailyData.getClose() >= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice1(1);
			}else if(baseCfgBean.getDiyPrice1()<0 & currentTDXDailyData.getClose() <= markTDXDailyData.getClose()*(1-comparePrice)){
				reportRecord.setDiyPrice1(1);
			}
			
			comparePrice = baseCfgBean.getDiyPrice2();
			if(baseCfgBean.getDiyPrice2()>0 & currentTDXDailyData.getClose() >= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice2(1);
			}else if(baseCfgBean.getDiyPrice2()<0 & currentTDXDailyData.getClose() <= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice2(1);
			}
			
			comparePrice = baseCfgBean.getDiyPrice3();
			if(baseCfgBean.getDiyPrice3()>0 & currentTDXDailyData.getClose() >= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice3(1);
			}else if(baseCfgBean.getDiyPrice3()<0 & currentTDXDailyData.getClose() <= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice3(1);
			}
			
			comparePrice = baseCfgBean.getDiyPrice4();
			if(baseCfgBean.getDiyPrice4()>0 & currentTDXDailyData.getClose() >= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice4(1);
			}else if(baseCfgBean.getDiyPrice4()<0 & currentTDXDailyData.getClose() <= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice4(1);
			}
			
			comparePrice = baseCfgBean.getDiyPrice5();
			if(baseCfgBean.getDiyPrice5()>0 & currentTDXDailyData.getClose() >= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice5(1);
			}else if(baseCfgBean.getDiyPrice5()<0 & currentTDXDailyData.getClose() <= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice5(1);
			}
			
			
			comparePrice = baseCfgBean.getDiyPrice6();
			if(baseCfgBean.getDiyPrice6()>0 & currentTDXDailyData.getClose() >= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice6(1);
			}else if(baseCfgBean.getDiyPrice6()<0 & currentTDXDailyData.getClose() <= markTDXDailyData.getClose()*(1+comparePrice)){
				reportRecord.setDiyPrice6(1);
			}
			
			System.out.println("finish," + reportRecord);
			reportRecordList.add(reportRecord);
		}
		
		return reportRecordList;
	}
	
	public void test(){
		String currDate = StockDateUtil.getNowDate();
		TDXDailyDataService service = new TDXDailyDataService();
		
		System.out.println(service.getDailyByDate2("600298", "20160714"));
		
	}
	
//	public static void main(String[] args) {
//		new DateReportService().test();
//		
//		
//	}
}
