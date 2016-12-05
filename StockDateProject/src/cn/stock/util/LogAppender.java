package cn.stock.util;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

public class LogAppender extends DailyRollingFileAppender {  

	public boolean isAsSevereAsThreshold(Priority priority) {
		return this.getThreshold().equals(priority); 
	}  
	
}  
