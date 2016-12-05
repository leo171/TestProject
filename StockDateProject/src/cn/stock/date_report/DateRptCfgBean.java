package cn.stock.date_report;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Date Report 配置模板对象
 * @author Administrator
 *
 */
public class DateRptCfgBean extends DateReportBean{
	private List<Object> header = new ArrayList<Object>();

	public DateRptCfgBean(){
		
	}
	
	public DateRptCfgBean(String stockNo, String stockName, String markDate) {
		super(stockNo, stockName, markDate);
	}

	public List<Object> getHeader() {
		return header;
	}

	public void setHeader(List<Object> header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "DateRptCfgBean [header=" + header + "] [super]" + super.toString() ;
	}
}
