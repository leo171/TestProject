package cn.stock.entity;

import cn.stock.entity.DailyData;

public class TDXDailyData extends DailyData{
	protected String id;
	protected String stockCode;
	
	public TDXDailyData(){}
	
	public TDXDailyData(String stockCode,String[] dataArray){
		setDataFromFile(stockCode, dataArray);
	}
	
	public TDXDailyData setDataFromFile(String stockCode,String[] dataArray){
		this.stockCode = stockCode;
		
		this.date = Integer.parseInt(dataArray[3] + dataArray[2] +dataArray[1] +dataArray[0], 16) +"";
		this.id = stockCode+date;
		this.open =  Integer.parseInt(dataArray[7] + dataArray[6] + dataArray[5] + dataArray[4], 16) / 100.0 ;
		this.high =  Integer.parseInt(dataArray[11] + dataArray[10] + dataArray[9] + dataArray[8], 16) / 100.0 ;
		this.low =  Integer.parseInt(dataArray[15] + dataArray[14] + dataArray[13] + dataArray[12], 16) / 100.0 ;
		this.close =  Integer.parseInt(dataArray[19] + dataArray[18] + dataArray[17] + dataArray[16], 16) / 100.0 ;
		this.vol =  Long.parseLong(dataArray[27] + dataArray[26] + dataArray[25] + dataArray[24], 16) / 100 ;
		return this;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		return  date + "," + open + "," + close + "," + low + "," + high + "," + vol;
	}
	
	public String toExcelString(){
		return "[" + stockCode + "]\t" + date + "\nOpen\t" +  open + "\nClose\t" + close + "\nLow\t" + low + "\nHigh\t" + high + "\nVol\t" + vol;   
	}
	
	public String toJSONString() {
		return "["+ date + "," + open + "," + close + "," + low + "," + high + "]";
	}
	
}
