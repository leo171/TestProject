package cn.stock.date_report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.stock.common.helper.ConfigHelper;
import cn.stock.common.helper.XlsxHelper;
import cn.stock.common.util.DateUtil;
import cn.stock.common.util.FileUtil;
import cn.stock.common.util.TextUtil;

public class DateReportPrinter {
	private String outputfile ;
	private String blankExcel ;
	private XlsxHelper xlsxHelper = new XlsxHelper();
	private DateReportService reportService ;
	private DateRptCfgLoader loader; 
	
	private int maxColumnMax = 0;
	private int[] indexArray;
	
	private Logger logger;
	
	public DateReportPrinter(){
		logger = Logger.getLogger(this.getClass().getName());
		try{
			loader = new DateRptCfgLoader(); 
			reportService = new DateReportService();
			outputfile = ConfigHelper.getDefKey("DAY_PRT_OUTPUT");
			blankExcel = ConfigHelper.getDefKey("BLANK_EXCEL_FILE");
			maxColumnMax = getMaxColumnMax();
		}catch(Exception re){
			logger.error(re.getMessage());
		}
	}
	
	private int getMaxColumnMax() {
		maxColumnMax = 0;
		for(Integer index : loader.getColumnIndexMap().values()){
			if(maxColumnMax < index){
				maxColumnMax = index;
			}
		}
		indexArray = new int[maxColumnMax];
		for(int i = 0;i<indexArray.length;i++){
			indexArray[i] = i;
		}
		return maxColumnMax+1; //索引 ， 从0开始，所以要+1
	}
	
//	private Object[]

	public void printReport(){
		if(!FileUtil.copyFile(blankExcel, outputfile)){
			System.out.println("report 文件创建失败");
			return;
		}
		List<Object> header = new DateRptCfgLoader().getHeaderRecord();

		Workbook workbook = xlsxHelper.createWorkbookFromFile(outputfile);
		Sheet sheet = workbook.getSheetAt(0);
		try {
			xlsxHelper.setRowHeaderContent(0, sheet,header.toArray());
			List<DateReportBean> dateReportRecoreds = reportService.handleDatas();
			for(int i = 0;i<dateReportRecoreds.size();i++){
				Object reportRecord[] = beanConvertToRecord(dateReportRecoreds.get(i));
				
				xlsxHelper.setRowHeaderContent(i+1, sheet,reportRecord);
			}
			workbook.write(new FileOutputStream(outputfile));
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	private Object[] handleHeader(){
//		String[] strMarkDays = TextUtil.nullValueConvert(ConfigHelper.getDefKey("DAY_RPT_MARK_DAYS")).split(",");
//		
//		Integer indexOfStockNo = 0 , indexOfStockName= 1 ,indexOfMarkDate= 2;
//		Map<Integer, String> headerMap = new HashMap<Integer, String>();
//		
//		
//		if(TextUtil.nullValueConvert(ConfigHelper.getDefKey("DAY_RPT_STOCK_NO_COLUMN")).length()>0){
//			indexOfStockNo = xlsxHelper.convertColumnName(ConfigHelper.getDefKey("DAY_RPT_STOCK_NO_COLUMN"));
//		}
//		
//		if(TextUtil.nullValueConvert(ConfigHelper.getDefKey("DAY_RPT_STOCK_NAME_COLUMN")).length()>0){
//			indexOfStockName = xlsxHelper.convertColumnName(ConfigHelper.getDefKey("DAY_RPT_STOCK_NAME_COLUMN"));
//		}
//		
//		if(TextUtil.nullValueConvert(ConfigHelper.getDefKey("DAY_RPT_MARK_DATE_COLUMN")).length()>0){
//			indexOfMarkDate = xlsxHelper.convertColumnName(ConfigHelper.getDefKey("DAY_RPT_MARK_DATE_COLUMN"));
//		}
//		headerMap.put(indexOfStockNo, "股票代码");
//	}
	
	private Object[] beanConvertToRecord(DateReportBean dateReportBean) {
		Object[] record = new Object[maxColumnMax];
//		int[] tempIndexArray = Arrays.copyOf(indexArray, indexArray.length);
		
		for(Map.Entry<String, Integer> entry : loader.getColumnIndexMap().entrySet()){
			if(entry.getKey().equals("DAY_RPT_STOCK_NO_COLUMN")){
				record[entry.getValue()]=dateReportBean.getStockCode();
			}else if(entry.getKey().equals("DAY_RPT_STOCK_NAME_COLUMN")){
				record[entry.getValue()]=dateReportBean.getStockName();
			}else if(entry.getKey().equals("DAY_RPT_MARK_DATE_COLUMN")){
				record[entry.getValue()]=dateReportBean.getMarkDate();
			}else if(entry.getKey().equals("DAY_RPT_MARK_CLOSE_PRICE")){
				record[entry.getValue()]=dateReportBean.getMarkClosePrice();
			}else if(entry.getKey().equals("DAY_RPT_ABS_PRICE")){
				record[entry.getValue()]=dateReportBean.getAbsPriceOfToday();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_5")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning5();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_5_VALUE")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning5ClosePrice();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_8")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning8();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_8_VALUE")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning8ClosePrice();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_13")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning13();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_13_VALUE")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning13ClosePrice();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_21")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning21();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_21_VALUE")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning21ClosePrice();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_34")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning34();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_34_VALUE")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning34ClosePrice();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_55")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning55();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_55_VALUE")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning55ClosePrice();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_89")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning89();
			}else if(entry.getKey().equals("DAY_RPT_WARNING_89_VALUE")){
				record[entry.getValue()]=dateReportBean.getDayOfWarning89ClosePrice();
			}else if(entry.getKey().equals("DAY_PRT_WARNING_DIY_RANGE")){
				record[entry.getValue()]=dateReportBean.getPriceWarningArea();
			}else if(entry.getKey().equals("DAY_PRT_WARNING_DIY_RANGE_RESULT")){
				record[entry.getValue()]=dateReportBean.getPriceWarningAreaFlag();
			}else if(entry.getKey().equals("DAY_PRT_WARNING_MIN_VOL")){
				record[entry.getValue()]=dateReportBean.getWarningMinVol();
			}else if(entry.getKey().equals("DAY_PRT_WARNING_MIN_PRICE")){
				record[entry.getValue()]=dateReportBean.getWarningMinPrice();
			}else if(entry.getKey().equals("DAY_PRT_BOX_TOP")){
				record[entry.getValue()]=dateReportBean.getBoxTopPrice();
			}else if(entry.getKey().equals("DAY_PRT_BOX_3_4")){
				record[entry.getValue()]=dateReportBean.getBox3_4Price();
			}else if(entry.getKey().equals("DAY_PRT_BOX_MIDDLE")){
				record[entry.getValue()]=dateReportBean.getBoxMiddlePrice();
			}else if(entry.getKey().equals("DAY_PRT_BOX_1_4")){
				record[entry.getValue()]=dateReportBean.getBox1_4Price();
			}else if(entry.getKey().equals("DAY_PRT_BOX_LOW")){
				record[entry.getValue()]=dateReportBean.getBoxLowPrice();
			}else if(entry.getKey().equals("DAY_PRT_BOX_NOW")){
				record[entry.getValue()]=dateReportBean.getCurrentPriceInBox();
			}else if(entry.getKey().equals("DAY_PRT_DIY_PRICE_1")){
				record[entry.getValue()]=dateReportBean.getDiyPrice1();
			}else if(entry.getKey().equals("DAY_PRT_DIY_PRICE_2")){
				record[entry.getValue()]=dateReportBean.getDiyPrice2();
			}else if(entry.getKey().equals("DAY_PRT_DIY_PRICE_3")){
				record[entry.getValue()]=dateReportBean.getDiyPrice3();
			}else if(entry.getKey().equals("DAY_PRT_DIY_PRICE_4")){
				record[entry.getValue()]=dateReportBean.getDiyPrice4();
			}else if(entry.getKey().equals("DAY_PRT_DIY_PRICE_5")){
				record[entry.getValue()]=dateReportBean.getDiyPrice5();
			}else if(entry.getKey().equals("DAY_PRT_DIY_PRICE_6")){
				record[entry.getValue()]=dateReportBean.getDiyPrice6();
			}
//			System.out.println(record.length);
		}
		return record;
	}

	public static void main(String[] args) {
		DateReportPrinter dateReportPrinter = new DateReportPrinter();
		dateReportPrinter.printReport();
//		dateReportPrinter.getMaxColumnMax();
		
		
	}
}

/*
DAY_RPT_STOCK_NO_COLUMN=A
DAY_RPT_STOCK_NAME_COLUMN=B
DAY_RPT_MARK_DATE_COLUMN=C
DAY_RPT_MARK_CLOSE_PRICE=D
DAY_RPT_ABS_PRICE=E
DAY_RPT_WARNING_5=F
DAY_RPT_WARNING_5_VALUE=G
DAY_RPT_WARNING_8=H
DAY_RPT_WARNING_8_VALUE=I
DAY_RPT_WARNING_13=J
DAY_RPT_WARNING_13_VALUE=K
DAY_RPT_WARNING_21=L
DAY_RPT_WARNING_21_VALUE=M
DAY_RPT_WARNING_34=N
DAY_RPT_WARNING_34_VALUE=O
DAY_RPT_WARNING_55=P
DAY_RPT_WARNING_55_VALUE=Q
DAY_RPT_WARNING_89=R
DAY_RPT_WARNING_89_VALUE=S
DAY_PRT_WARNING_DIY_RANGE=T
DAY_PRT_WARNING_DIY_RANGE_RESULT=U
DAY_PRT_WARNING_MIN_VOL=V
DAY_PRT_WARNING_MIN_PRICE=W
DAY_PRT_BOX_TOP=X
DAY_PRT_BOX_3_4=Y
DAY_PRT_BOX_MIDDLE=Z
DAY_PRT_BOX_1_4=AA
DAY_PRT_BOX_LOW=AB
DAY_PRT_BOX_NOW=AC
DAY_PRT_DIY_PRICE_1=AD
DAY_PRT_DIY_PRICE_2=AE
DAY_PRT_DIY_PRICE_3=AF
DAY_PRT_DIY_PRICE_4=AG
DAY_PRT_DIY_PRICE_5=AH


DAY_RPT_STOCK_NO_COLUMN"
DAY_RPT_STOCK_NAME_COLUMN"
DAY_RPT_MARK_DATE_COLUMN"
DAY_RPT_MARK_CLOSE_PRICE"
DAY_RPT_ABS_PRICE"
DAY_RPT_WARNING_5"
DAY_RPT_WARNING_5_VALUE"
DAY_RPT_WARNING_8"
DAY_RPT_WARNING_8_VALUE"
DAY_RPT_WARNING_13"
DAY_RPT_WARNING_13_VALUE"
DAY_RPT_WARNING_21"
DAY_RPT_WARNING_21_VALUE"
DAY_RPT_WARNING_34"
DAY_RPT_WARNING_34_VALUE"
DAY_RPT_WARNING_55"
DAY_RPT_WARNING_55_VALUE"
DAY_RPT_WARNING_89"
DAY_RPT_WARNING_89_VALUE"
DAY_PRT_WARNING_DIY_RANGE"
DAY_PRT_WARNING_DIY_RANGE_RESULT"
DAY_PRT_WARNING_MIN_VOL"
DAY_PRT_WARNING_MIN_PRICE"
DAY_PRT_BOX_TOP"
DAY_PRT_BOX_3_4"
DAY_PRT_BOX_MIDDLE"
DAY_PRT_BOX_1_4"
DAY_PRT_BOX_LOW"
DAY_PRT_BOX_NOW"
DAY_PRT_DIY_PRICE_1"
DAY_PRT_DIY_PRICE_2"
DAY_PRT_DIY_PRICE_3"
DAY_PRT_DIY_PRICE_4"
DAY_PRT_DIY_PRICE_5"

 */
