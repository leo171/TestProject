package cn.stock.date_report;

public class DateReportBean {
	/**股票代码  */
	private String stockCode;
	/**股票日期 */
	private String stockName;
	/**标记日期 */
	private String markDate;
	/**标记收盘价*/
	private Double markClosePrice;
	/**当天偏离值 (区间的最高/低值)*/
	private Double absPriceOfToday;
	/**5日相差日数*/
	private int dayOfWarning5;
	/**5日偏离值(收盘价)*/
	private double dayOfWarning5ClosePrice;
	/**8日相差日数*/
	private int dayOfWarning8;
	/**8日偏离值(收盘价)*/
	private double dayOfWarning8ClosePrice;
	/**13日相差日数*/
	private int dayOfWarning13;
	/**13日偏离值(收盘价)*/
	private double dayOfWarning13ClosePrice;
	/**21日相差日数*/
	private int dayOfWarning21;
	/**21日偏离值(收盘价)*/
	private double dayOfWarning21ClosePrice;
	/**34日相差日数*/
	private int dayOfWarning34;
	/**34日偏离值(收盘价)*/
	private double dayOfWarning34ClosePrice;
	/**55日相差日数*/
	private int dayOfWarning55;
	/**55日偏离值(收盘价)*/
	private double dayOfWarning55ClosePrice;
	/**89日相差日数*/
	private int dayOfWarning89;
	/**89日偏离值(收盘价)*/
	private double dayOfWarning89ClosePrice;
	/**预置区间提示*/
	private String priceWarningArea;
	/**预置区间提示结果*/
	private double priceWarningAreaFlag;
	/** 区间最小量提醒*/
	private double warningMinVol;
	/** 区间最低价格 */
	private double warningMinPrice;
	/** 箱体顶 */
	private double boxTopPrice;
	/** 3/4 箱体 */
	private double box3_4Price;
	/** 箱体中位线*/
	private double boxMiddlePrice;
	/** 1/4 箱体*/
	private double box1_4Price;
	/** 箱体箱底*/
	private double boxLowPrice;
	/** 当前价格与箱体的位置 */
	private double currentPriceInBox;
	/** 自定义价格1*/
	private double diyPrice1;
	/** 自定义价格2*/
	private double diyPrice2;
	/** 自定义价格3*/
	private double diyPrice3;
	/** 自定义价格4*/
	private double diyPrice4;
	/** 自定义价格5*/
	private double diyPrice5;
	/** 自定义价格6*/
	private double diyPrice6;
	
	
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getMarkDate() {
		return markDate;
	}
	public void setMarkDate(String markDate) {
		this.markDate = markDate;
	}
	public Double getMarkClosePrice() {
		return markClosePrice;
	}
	public void setMarkClosePrice(Double markClosePrice) {
		this.markClosePrice = markClosePrice;
	}
	public Double getAbsPriceOfToday() {
		return absPriceOfToday;
	}
	public void setAbsPriceOfToday(Double absPriceOfToday) {
		this.absPriceOfToday = absPriceOfToday;
	}
	public int getDayOfWarning5() {
		return dayOfWarning5;
	}
	public void setDayOfWarning5(int dayOfWarning5) {
		this.dayOfWarning5 = dayOfWarning5;
	}
	public double getDayOfWarning5ClosePrice() {
		return dayOfWarning5ClosePrice;
	}
	public void setDayOfWarning5ClosePrice(double dayOfWarning5ClosePrice) {
		this.dayOfWarning5ClosePrice = dayOfWarning5ClosePrice;
	}
	public int getDayOfWarning8() {
		return dayOfWarning8;
	}
	public void setDayOfWarning8(int dayOfWarning8) {
		this.dayOfWarning8 = dayOfWarning8;
	}
	public double getDayOfWarning8ClosePrice() {
		return dayOfWarning8ClosePrice;
	}
	public void setDayOfWarning8ClosePrice(double dayOfWarning8ClosePrice) {
		this.dayOfWarning8ClosePrice = dayOfWarning8ClosePrice;
	}
	public int getDayOfWarning13() {
		return dayOfWarning13;
	}
	public void setDayOfWarning13(int dayOfWarning13) {
		this.dayOfWarning13 = dayOfWarning13;
	}
	public double getDayOfWarning13ClosePrice() {
		return dayOfWarning13ClosePrice;
	}
	public void setDayOfWarning13ClosePrice(double dayOfWarning13ClosePrice) {
		this.dayOfWarning13ClosePrice = dayOfWarning13ClosePrice;
	}
	public int getDayOfWarning21() {
		return dayOfWarning21;
	}
	public void setDayOfWarning21(int dayOfWarning21) {
		this.dayOfWarning21 = dayOfWarning21;
	}
	public double getDayOfWarning21ClosePrice() {
		return dayOfWarning21ClosePrice;
	}
	public void setDayOfWarning21ClosePrice(double dayOfWarning21ClosePrice) {
		this.dayOfWarning21ClosePrice = dayOfWarning21ClosePrice;
	}
	public int getDayOfWarning34() {
		return dayOfWarning34;
	}
	public void setDayOfWarning34(int dayOfWarning34) {
		this.dayOfWarning34 = dayOfWarning34;
	}
	public double getDayOfWarning34ClosePrice() {
		return dayOfWarning34ClosePrice;
	}
	public void setDayOfWarning34ClosePrice(double dayOfWarning34ClosePrice) {
		this.dayOfWarning34ClosePrice = dayOfWarning34ClosePrice;
	}
	public int getDayOfWarning55() {
		return dayOfWarning55;
	}
	public void setDayOfWarning55(int dayOfWarning55) {
		this.dayOfWarning55 = dayOfWarning55;
	}
	public double getDayOfWarning55ClosePrice() {
		return dayOfWarning55ClosePrice;
	}
	public void setDayOfWarning55ClosePrice(double dayOfWarning55ClosePrice) {
		this.dayOfWarning55ClosePrice = dayOfWarning55ClosePrice;
	}
	public int getDayOfWarning89() {
		return dayOfWarning89;
	}
	public void setDayOfWarning89(int dayOfWarning89) {
		this.dayOfWarning89 = dayOfWarning89;
	}
	public double getDayOfWarning89ClosePrice() {
		return dayOfWarning89ClosePrice;
	}
	public void setDayOfWarning89ClosePrice(double dayOfWarning89ClosePrice) {
		this.dayOfWarning89ClosePrice = dayOfWarning89ClosePrice;
	}
	public String getPriceWarningArea() {
		return priceWarningArea;
	}
	public void setPriceWarningArea(String priceWarningArea) {
		this.priceWarningArea = priceWarningArea;
	}
	public double getPriceWarningAreaFlag() {
		return priceWarningAreaFlag;
	}
	public void setPriceWarningAreaFlag(double priceWarningAreaFlag) {
		this.priceWarningAreaFlag = priceWarningAreaFlag;
	}
	public double getWarningMinVol() {
		return warningMinVol;
	}
	public void setWarningMinVol(double warningMinVol) {
		this.warningMinVol = warningMinVol;
	}
	public double getWarningMinPrice() {
		return warningMinPrice;
	}
	public void setWarningMinPrice(double warningMinPrice) {
		this.warningMinPrice = warningMinPrice;
	}
	public double getBoxTopPrice() {
		return boxTopPrice;
	}
	public void setBoxTopPrice(double boxTopPrice) {
		this.boxTopPrice = boxTopPrice;
	}
	public double getBox3_4Price() {
		return box3_4Price;
	}
	public void setBox3_4Price(double box3_4Price) {
		this.box3_4Price = box3_4Price;
	}
	public double getBoxMiddlePrice() {
		return boxMiddlePrice;
	}
	public void setBoxMiddlePrice(double boxMiddlePrice) {
		this.boxMiddlePrice = boxMiddlePrice;
	}
	public double getBox1_4Price() {
		return box1_4Price;
	}
	public void setBox1_4Price(double box1_4Price) {
		this.box1_4Price = box1_4Price;
	}
	public double getBoxLowPrice() {
		return boxLowPrice;
	}
	public void setBoxLowPrice(double boxLowPrice) {
		this.boxLowPrice = boxLowPrice;
	}
	public double getCurrentPriceInBox() {
		return currentPriceInBox;
	}
	public void setCurrentPriceInBox(double currentPriceInBox) {
		this.currentPriceInBox = currentPriceInBox;
	}
	public double getDiyPrice1() {
		return diyPrice1;
	}
	public void setDiyPrice1(double diyPrice1) {
		this.diyPrice1 = diyPrice1;
	}
	public double getDiyPrice2() {
		return diyPrice2;
	}
	public void setDiyPrice2(double diyPrice2) {
		this.diyPrice2 = diyPrice2;
	}
	public double getDiyPrice3() {
		return diyPrice3;
	}
	public void setDiyPrice3(double diyPrice3) {
		this.diyPrice3 = diyPrice3;
	}
	public double getDiyPrice4() {
		return diyPrice4;
	}
	public void setDiyPrice4(double diyPrice4) {
		this.diyPrice4 = diyPrice4;
	}
	public double getDiyPrice5() {
		return diyPrice5;
	}
	public void setDiyPrice5(double diyPrice5) {
		this.diyPrice5 = diyPrice5;
	}
	public double getDiyPrice6() {
		return diyPrice6;
	}
	public void setDiyPrice6(double diyPrice6) {
		this.diyPrice6 = diyPrice6;
	}
	public DateReportBean(){
	}
	public DateReportBean(String stockCode, String stockName, String markDate) {
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.markDate = markDate;
	}
	@Override
	public String toString() {
		return "DateReportBean [stockCode=" + stockCode + ", stockName=" + stockName + ", markDate=" + markDate
				+ ", markClosePrice=" + markClosePrice + ", absPriceOfToday=" + absPriceOfToday + ", dayOfWarning5="
				+ dayOfWarning5 + ", dayOfWarning5ClosePrice=" + dayOfWarning5ClosePrice + ", dayOfWarning8="
				+ dayOfWarning8 + ", dayOfWarning8ClosePrice=" + dayOfWarning8ClosePrice + ", dayOfWarning13="
				+ dayOfWarning13 + ", dayOfWarning13ClosePrice=" + dayOfWarning13ClosePrice + ", dayOfWarning21="
				+ dayOfWarning21 + ", dayOfWarning21ClosePrice=" + dayOfWarning21ClosePrice + ", dayOfWarning34="
				+ dayOfWarning34 + ", dayOfWarning34ClosePrice=" + dayOfWarning34ClosePrice + ", dayOfWarning55="
				+ dayOfWarning55 + ", dayOfWarning55ClosePrice=" + dayOfWarning55ClosePrice + ", dayOfWarning89="
				+ dayOfWarning89 + ", dayOfWarning89ClosePrice=" + dayOfWarning89ClosePrice + ", priceWarningArea="
				+ priceWarningArea + ", priceWarningAreaFlag=" + priceWarningAreaFlag + ", warningMinVol="
				+ warningMinVol + ", warningMinPrice=" + warningMinPrice + ", boxTopPrice=" + boxTopPrice
				+ ", box3_4Price=" + box3_4Price + ", boxMiddlePrice=" + boxMiddlePrice + ", box1_4Price=" + box1_4Price
				+ ", boxLowPrice=" + boxLowPrice + ", currentPriceInBox=" + currentPriceInBox + ", diyPrice1="
				+ diyPrice1 + ", diyPrice2=" + diyPrice2 + ", diyPrice3=" + diyPrice3 + ", diyPrice4=" + diyPrice4
				+ ", diyPrice5=" + diyPrice5 + ", diyPrice6=" + diyPrice6 + "]";
	}
	public Object[] toArray() {
		return null;
	}
}
