package cn.stock.entity;

public class DailyData extends KData{
	protected String date;
	protected Long vol;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getVol() {
		return vol;
	}
	public void setVol(Long vol) {
		this.vol = vol;
	}
	public String[] toArray(){
		return new String[]{date, open+"",close+"",high+"",low+"",vol+""};
	}
	
	public String toString() {
		return date + "," + open + "," + close + "," + high + "," + low + "," + vol;
	}
}
