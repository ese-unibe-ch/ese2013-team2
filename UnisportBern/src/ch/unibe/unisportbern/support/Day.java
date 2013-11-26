package ch.unibe.unisportbern.support;

import java.util.Calendar;

public class Day {
	
	private String[] days;
	private int dayWanted;
	private String day;
	private int nextDay;
	private int month;
	private int year;
	
	public Day(String day){
		this.days = new String[]{"So","Mo", "Di", "Mi", "Do", "Fr", "Sa"};
		this.day = day;
	}
	
	public String getNextDay(){
		
		Calendar c = Calendar.getInstance();
		
		setDayWanted(this.day);
		
		//Difference between today and the day wanted
		this.controlDateChange(c, this.calculateOffset(c));
		
		return this.nextDay + "."+ this.month + "."  + this.year;
	}
	
	private void setDayWanted(String day){
		for(int a=0;a<7;a++){
			if(this.days[a].equals(day)){
				this.dayWanted = a;
			}
		}
	}
	
	private int calculateOffset(Calendar c){
		int today = c.get(Calendar.DAY_OF_WEEK);
		int offset = 0;

		if(dayWanted+1 < today){
			dayWanted += 7;
		}
		
		while(today <= dayWanted){
			offset ++;
			today ++;
		}
		
		return offset;
	}
	
	private void controlDateChange(Calendar c, int offset){
		this.nextDay = c.get(Calendar.DAY_OF_MONTH)+offset;
		this.month = c.get(Calendar.MONTH);
		this.year = c.get(Calendar.YEAR);
		
		if(this.nextDay+offset >= c.getActualMaximum(Calendar.DAY_OF_MONTH)){
			this.nextDay = this.nextDay+offset - c.getActualMaximum(Calendar.DAY_OF_MONTH);
			this.month += 1;
			
			if(this.month > 11) {
				this.year += 1;
				this.month = 0;
			}
		}
	}
}