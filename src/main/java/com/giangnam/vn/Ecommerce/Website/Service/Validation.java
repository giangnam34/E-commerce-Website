package com.giangnam.vn.Ecommerce.Website.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	private String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$";

	public boolean dayAfterDay(Date startDay, Date endDay) {
		return endDay.after(startDay);
	}
	
	public boolean isPresentDay(Date checkDay) {
		return LocalDate.now().isEqual(checkDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
	public boolean isPastDay(Date checkDay) {
		return LocalDate.now().isAfter(checkDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
	public boolean isFutureDay(Date checkDay) {
		return LocalDate.now().isBefore(checkDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
	public boolean isValidPassWord(String password) {
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
	}
	
	
}
