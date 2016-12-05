package cn.stock.entity;

public class KData {
	protected double open;
	protected double close;
	protected double high;
	protected double low;
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	
	public String[] toArray(){
		return new String[]{open+"",close+"",high+"",low+""};
	}
}
