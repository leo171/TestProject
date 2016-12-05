package cn.stock.parster;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import cn.stock.entity.TDXDailyData;

public class TDXPaster {
//	private final static int STYLE_BY_DAY = 0;
//	private final static int STYLE_BY_WEEK = 1;
//	private final static int STYLE_BY_MONTH = 2;
	private static final int READ_32BYTE = 32;
	private long fileLength = 0;
	private RandomAccessFile rf;
	
	/**
	 * 通过二分法，找到日期对应的day文件指针位置
	 * @param rf 			文件对象
	 * @param beginIndex 	开始索引指针
	 * @param endIndex		结束索引指针
	 * @param baseIndex		中间计算指针 （初始调用时应为0）
	 * @param compareDate	日期
	 * @return
	 */
	public long seekPoint(RandomAccessFile rf ,long beginIndex,long endIndex,long baseIndex,int compareDate){
		long index = baseIndex + (endIndex - beginIndex)/2;
		if(index == beginIndex || index == endIndex){
			return index;
		}
//		TDXDailyData TDXDailyData = new TDXDailyData("", handlingData(index *32));
		String[] dataArray = handlingData(index *32);
		//Integer.parseInt(dataArray[3] + dataArray[2] +dataArray[1] +dataArray[0], 16) +""
//		int dateFromFile = Integer.parseInt(TDXDailyData.getDate());
		int dateFromFile = Integer.parseInt(dataArray[3] + dataArray[2] +dataArray[1] +dataArray[0], 16);
		if(compareDate > dateFromFile){
			beginIndex = baseIndex = index ;
			return seekPoint(rf,beginIndex,endIndex,baseIndex,compareDate);
		}else if(compareDate < dateFromFile){
			beginIndex = baseIndex ;
			endIndex = index;
			return seekPoint(rf,beginIndex,endIndex,baseIndex,compareDate);
		}else{
			return index;
		}
	}
	
	/**
	 * 将一线32位的数据分解出来
	 * @param seekValue
	 * @return
	 */
	private String[] handlingData(long seekValue) {
		byte[] readBuff = new byte[READ_32BYTE];
		String[] result = new String[READ_32BYTE];
		try {
			rf.seek(seekValue);
			rf.read(readBuff);
			for(int i = 0;i<readBuff.length;i++){
				if(readBuff[i]<0){
					result[i] = Integer.toHexString(readBuff[i]).substring(6);
				}else{
					result[i] = Integer.toHexString(readBuff[i]);
				}
				if(result[i].length()==1){
					result[i]= "0" +  result[i];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取指定日期的日线数据
	 * @param dayFileName
	 * @param date
	 * @return
	 */
	public TDXDailyData getDataByOneDay(String dayFileName,String date){
		List<TDXDailyData> dataList = getDataByDays(dayFileName, date, date);
		if(dataList.size()>0){
			return dataList.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 根据日期范围(startDate - endDate从day文件获取日线数据
	 * @param dayFileName day文件
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<TDXDailyData> getDataByDays(String dayFileName,String startDate,String endDate){
		List<TDXDailyData> dataList = new ArrayList<TDXDailyData>();
		String msg = validateFile(dayFileName);
		if(msg.length()!=6){
			System.out.println(msg);
			return dataList;
		}
		//计算startDate 和 endDate 的文件位置
		long startDatePoint = seekPoint(rf, 0, fileLength/32, 0, Integer.parseInt(startDate)) *32 ;
		long endDatePoint = seekPoint(rf, 0, fileLength/32, 0, Integer.parseInt(endDate)) *32 ;
		
		//处理第一日的数据
		String[] startValue = handlingData(startDatePoint);
		TDXDailyData TDXDailyData = new TDXDailyData(msg, startValue);
		if(!TDXDailyData.getDate().equals(startDate) && startDate.equals(endDate)){
			return dataList;
		}else if(!TDXDailyData.getDate().equals(startDate) && !startDate.equals(endDate)){
			startDatePoint+=32;
		}
		//处理多于一日的数据
		for(long i = startDatePoint;i<=endDatePoint;i+=READ_32BYTE){
			String[] value = handlingData(i);
			TDXDailyData = new TDXDailyData(msg, value);
			dataList.add(TDXDailyData);
		}
		return dataList;
	}
	
	/**
	 * 验证文件是否有效（长度为32位的整数）
	 * @param dayFileName
	 * @return
	 */
	private String validateFile(String dayFileName){
		String msg = "";
		try {
			rf = new RandomAccessFile(dayFileName, "r");
			if(!lengthVaildate(rf)){
				msg = "不正常的Date文件(文件长度不是32位整数)";
			}else{
				msg = dayFileName.substring(dayFileName.length()-10,dayFileName.length()-4);
				try{
					Integer.parseInt(msg);
				}catch (Exception e) {
					msg = "不正常的Date文件(文件长度不是32位整数)";
				}
			}
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.err.println(e.getMessage());
			msg = e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}
	
	/**
	 * 验证通达信文件的长度是否为32的倍数，
	 * @param rf
	 * @return
	 * @throws IOException
	 */
	private boolean lengthVaildate(RandomAccessFile rf) throws IOException {
		if (rf.length() % 32 != 0) {
			return false;
		} else {
			fileLength = rf.length();
			return true;
		}
	}
}
