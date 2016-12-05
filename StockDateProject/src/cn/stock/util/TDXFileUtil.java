package cn.stock.util;

import cn.stock.common.helper.ConfigHelper;

public class TDXFileUtil {
//	private static ConfigHelper config = new ReadConfig();
	/**
	 * 获取day文件路径全名
	 * @param stockCode 股票代码
	 * @return
	 */
	public static String getStrTDXFile(String stockCode){
		String dayFileName = getPrefixStockNo(stockCode);
		try {
			if(dayFileName.substring(0, 2).equalsIgnoreCase("sh")){
				dayFileName = ConfigHelper.getDefKey("DAY_SH_PATH") + "/" + dayFileName + ".day";
			}else if(dayFileName.substring(0, 2).equalsIgnoreCase("sz")){
				dayFileName = ConfigHelper.getDefKey("DAY_SZ_PATH") + "/" + dayFileName + ".day";
			}else{
				
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return dayFileName;
	}
	
	/**
	 * 根据股票代码返回day文件的名称
	 * @param stockCode
	 * @return
	 */
	public static String getPrefixStockNo(String stockCode){
		String dailyFileName = "";
		try {
			Integer.valueOf(stockCode);
			if(stockCode==null || stockCode.length() !=6){
				throw new Exception();
			}else{
				if(stockCode.substring(0, 2).equals("00")){
					dailyFileName =  "sz" + stockCode;
				}else if(stockCode.subSequence(0,2).equals("60")){
					dailyFileName =  "sh" + stockCode;
				}else if(stockCode.subSequence(0,2).equals("30")){
					dailyFileName =  "sz" + stockCode;
				}
			}
		} catch (Exception e) {
			String errMsg = "股票代码格式不正确[" + stockCode +  "]";
			throw new IllegalArgumentException(errMsg);
		}
		return dailyFileName;
	}
	
	

	
}
