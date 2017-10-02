package com.adservice.campaign.util;

import java.time.LocalDateTime;

public class DateTimeUtility {
	
	public static LocalDateTime addSecondsToCreatedDateTime(LocalDateTime startDate, int seconds){
	    LocalDateTime changeDate = startDate.plusSeconds(seconds);
	    return changeDate;
	}
	
	
	public static boolean isCurrentTimeIsGreater(LocalDateTime createdDateTime, LocalDateTime currentDateTime){
		boolean result = false;
		if (createdDateTime.isAfter(currentDateTime)) {
			result = true;
		}
	    return result;
	}
	
	public static boolean isCurrentTimeIsLessThanCampignTime(LocalDateTime createdDateTime, LocalDateTime currentDateTime){
		boolean result = false;
		if (createdDateTime.isBefore(currentDateTime)) {
			result = true;
		}
	    return result;
	}
	
}
