package cn.stock;

import cn.stock.entity.TDXDailyData;
import cn.stock.service.TDXDailyDataService;
import cn.stock.util.StockDateUtil;

public class MainTest {
	public static void main(String[] args) {
		TDXDailyDataService service = new TDXDailyDataService();
		TDXDailyData data = service.getDailyByDate1("603377", "20161108");
		System.out.println(data.toExcelString());
		
		System.out.println("----");
		
		System.out.println(StockDateUtil.locateTransactionDate("20161021", 89-1));
		System.out.println(StockDateUtil.locateTransactionDate("20161114", 72));
		
		
//		StockDateUtil.locateTransactionDate(referenceDate, dayCount)
		
		
	}
}
