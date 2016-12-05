package cn.stock.date_report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.stock.common.helper.ConfigHelper;
import cn.stock.common.helper.XlsxHelper;
import cn.stock.common.util.TextUtil;

/**
 * Date Report 配置文件加载器
 * @author Administrator
 *
 */
public class DateRptCfgLoader extends XlsxHelper{
	public static String xlsxConfigFile = ConfigHelper.getDefKey("DAY_RPT_TEMPLATE_FILE");
	public Logger logger;
	private Map<String, Integer> columnIndexMap = new HashMap<String, Integer>();
	
	private List<Object> headerRecord = new ArrayList<>();
	
	public DateRptCfgLoader(){
		
		logger = Logger.getLogger(this.getClass().getName());
		if(!new File(xlsxConfigFile).exists()){
			throw new RuntimeException("Day Report 模板文件不存在.");
		}else{
			initialConfg();
		}
	}
	
	private void initialConfg() {
		//加载并读取配置文件内容
		Workbook workbook = createWorkbookFromFile(xlsxConfigFile);
		if(workbook == null){
			throw new RuntimeException("配置文件加载失败");
		}
		Sheet sheet = workbook.getSheetAt(0);
		List<List<Object>> excelContent = getSheetContent(sheet);
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(excelContent.size()>0){
			headerRecord = excelContent.get(0);
		}else{
			throw new RuntimeException("空配置文件");
		}
		
		columnIndexMap.put("DAY_RPT_STOCK_NO_COLUMN", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_STOCK_NO_COLUMN")));
		columnIndexMap.put("DAY_RPT_STOCK_NAME_COLUMN", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_STOCK_NAME_COLUMN")));
		columnIndexMap.put("DAY_RPT_MARK_DATE_COLUMN", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_MARK_DATE_COLUMN")));
		columnIndexMap.put("DAY_RPT_MARK_CLOSE_PRICE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_MARK_CLOSE_PRICE")));
		columnIndexMap.put("DAY_RPT_ABS_PRICE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_ABS_PRICE")));
		columnIndexMap.put("DAY_RPT_WARNING_5", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_5")));
		columnIndexMap.put("DAY_RPT_WARNING_5_VALUE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_5_VALUE")));
		columnIndexMap.put("DAY_RPT_WARNING_8", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_8")));
		columnIndexMap.put("DAY_RPT_WARNING_8_VALUE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_8_VALUE")));
		columnIndexMap.put("DAY_RPT_WARNING_13", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_13")));
		columnIndexMap.put("DAY_RPT_WARNING_13_VALUE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_13_VALUE")));
		columnIndexMap.put("DAY_RPT_WARNING_21", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_21")));
		columnIndexMap.put("DAY_RPT_WARNING_21_VALUE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_21_VALUE")));
		columnIndexMap.put("DAY_RPT_WARNING_34", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_34")));
		columnIndexMap.put("DAY_RPT_WARNING_34_VALUE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_34_VALUE")));
		columnIndexMap.put("DAY_RPT_WARNING_55", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_55")));
		columnIndexMap.put("DAY_RPT_WARNING_55_VALUE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_55_VALUE")));
		columnIndexMap.put("DAY_RPT_WARNING_89", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_89")));
		columnIndexMap.put("DAY_RPT_WARNING_89_VALUE", convertColumnName(ConfigHelper.getDefKey("DAY_RPT_WARNING_89_VALUE")));
		columnIndexMap.put("DAY_PRT_WARNING_DIY_RANGE", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_WARNING_DIY_RANGE")));
		columnIndexMap.put("DAY_PRT_WARNING_DIY_RANGE_RESULT", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_WARNING_DIY_RANGE_RESULT")));
		columnIndexMap.put("DAY_PRT_WARNING_MIN_VOL", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_WARNING_MIN_VOL")));
		columnIndexMap.put("DAY_PRT_WARNING_MIN_PRICE", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_WARNING_MIN_PRICE")));
		columnIndexMap.put("DAY_PRT_BOX_TOP", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_BOX_TOP")));
		columnIndexMap.put("DAY_PRT_BOX_3_4", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_BOX_3_4")));
		columnIndexMap.put("DAY_PRT_BOX_MIDDLE", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_BOX_MIDDLE")));
		columnIndexMap.put("DAY_PRT_BOX_1_4", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_BOX_1_4")));
		columnIndexMap.put("DAY_PRT_BOX_LOW", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_BOX_LOW")));
		columnIndexMap.put("DAY_PRT_BOX_NOW", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_BOX_NOW")));
		columnIndexMap.put("DAY_PRT_DIY_PRICE_1", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_1")));
		columnIndexMap.put("DAY_PRT_DIY_PRICE_2", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_2")));
		columnIndexMap.put("DAY_PRT_DIY_PRICE_3", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_3")));
		columnIndexMap.put("DAY_PRT_DIY_PRICE_4", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_4")));
		columnIndexMap.put("DAY_PRT_DIY_PRICE_5", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_5")));
		columnIndexMap.put("DAY_PRT_DIY_PRICE_6", convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_6")));
		
	}

	/**
	 * 获取配置数据
	 * @return 
	 */
	public List<DateRptCfgBean> getConfig(){
		//加载并读取配置文件内容
		Workbook workbook = createWorkbookFromFile(xlsxConfigFile);
		if(workbook == null){
			return null;
		}
		Sheet sheet = workbook.getSheetAt(0);
		List<List<Object>> excelContent = getSheetContent(sheet);
		
		
		// 封装配置数据
		List<DateRptCfgBean> dateRptCfg = new ArrayList<DateRptCfgBean>();
		for(int i = 0;i<excelContent.size();i++){
			DateRptCfgBean stockInfo = convertToDateRptCfg(excelContent.get(i),i);
			if(TextUtil.nullValueConvert(stockInfo.getStockCode()).length() > 0 || TextUtil.nullValueConvert(stockInfo.getMarkDate()).length() > 0){
				dateRptCfg.add(stockInfo);
			}
		}
		
		// 关闭配置文件
		try {
			workbook.close();
		} catch (IOException e) {
			logger.info("Close work book has some excepiton. - " + e.getMessage());
		}
		return dateRptCfg;
	}
	
	/**
	 * 封装配置数据
	 * @param dataRow
	 * @return
	 */
	private DateRptCfgBean convertToDateRptCfg(List<Object> dataRow,int currIndex) {
		String stockNo = TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_RPT_STOCK_NO_COLUMN"))));
		String stockName = TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_RPT_STOCK_NAME_COLUMN"))));
		String markDate = TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_RPT_MARK_DATE_COLUMN"))));
		DateRptCfgBean dateRptCfgBean = new DateRptCfgBean(stockNo, stockName, markDate);
		if(currIndex==0 ){
			dateRptCfgBean.setHeader(dataRow);
			/** 自定义价格1*/
			dateRptCfgBean.setDiyPrice1(Double.parseDouble(TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_1"))))));
			/** 自定义价格2*/
			dateRptCfgBean.setDiyPrice2(Double.parseDouble(TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_2"))))));
			/** 自定义价格3*/
			dateRptCfgBean.setDiyPrice3(Double.parseDouble(TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_3"))))));
			/** 自定义价格4*/
			dateRptCfgBean.setDiyPrice4(Double.parseDouble(TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_4"))))));
			/** 自定义价格5*/
			dateRptCfgBean.setDiyPrice5(Double.parseDouble(TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_PRT_DIY_PRICE_5"))))));
		}else{
			/**预置区间提示*/
			String tempValue = TextUtil.nullValueConvert(dataRow.get(convertColumnName(ConfigHelper.getDefKey("DAY_PRT_WARNING_DIY_RANGE"))));
			if(tempValue.length()>0){
				dateRptCfgBean.setPriceWarningArea(tempValue);
			}
			
		}
		return dateRptCfgBean;
	}
	
	public static void main(String[] args) {
		System.out.println(new DateRptCfgLoader().convertColumnName(("A")));
	}

	/**
	 * 获取配置文件中数据列输出的对应位置
	 * @return
	 */
	public Map<String, Integer> getColumnIndexMap() {
		return columnIndexMap;
	}

	public List<Object> getHeaderRecord() {
		return headerRecord;
	}

	public void setHeaderRecord(List<Object> headerRecord) {
		this.headerRecord = headerRecord;
	}
}
