package cn.stock.entity;

public class StockInfo {
	private String fldScode;
	private String fldGroup;
	private String fldUbegindate;
	private String fldUlastdate;
	private String fldConcept;
	private String fldIndustry;
	private String fldSname;
	public StockInfo(String stockCode) {
		this.fldScode = stockCode;
	}
	public StockInfo(){}
	public String getFldScode() {
		return fldScode;
	}
	public void setFldScode(String fldScode) {
		this.fldScode = fldScode;
	}
	public String getFldGroup() {
		return fldGroup;
	}
	public void setFldGroup(String fldGroup) {
		this.fldGroup = fldGroup;
	}
	public String getFldSname() {
		return fldSname;
	}
	public void setFldSname(String fldSname) {
		this.fldSname = fldSname;
	}
	public String getFldUbegindate() {
		return fldUbegindate;
	}
	public void setFldUbegindate(String fldUbegindate) {
		this.fldUbegindate = fldUbegindate;
	}
	public String getFldUlastdate() {
		return fldUlastdate;
	}
	public void setFldUlastdate(String fldUlastdate) {
		this.fldUlastdate = fldUlastdate;
	}
	
	public String getFldConcept() {
		return fldConcept;
	}
	public void setFldConcept(String fldConcept) {
		this.fldConcept = fldConcept;
	}
	public String getFldIndustry() {
		return fldIndustry;
	}
	public void setFldIndustry(String fldIndustry) {
		this.fldIndustry = fldIndustry;
	}
	@Override
	public String toString() {
		return "StockInfo [fldScode=" + fldScode + ", fldGroup=" + fldGroup + ", fldUbegindate=" + fldUbegindate
				+ ", fldUlastdate=" + fldUlastdate + ", fldConcept=" + fldConcept + ", fldIndustry=" + fldIndustry
				+ ", fldSname=" + fldSname + "]";
	}
}
