package ch.unibe.unisportbern.support;
import java.util.Date;

public class Day {
	
	private String[] days;
	private int dayWanted;
	
	public Day(String day){
		this.days = new String[]{"So","Mo", "Di", "Mi", "Do", "Fr", "Sa"};
		
		for(int a=0;a<7;a++){
			if(this.days[a].equals(day)){
				this.dayWanted = a;
			}
		}
	}
	
	public long getNextDay(){

		Date date = new Date();
		int isDay = date.getDay();
		int offset = 0;
		
		if(dayWanted < isDay){
			dayWanted += 7;
		}
		
		while(isDay != dayWanted){
			offset ++;
			isDay ++;
		}
				
		Date newDate = new Date(date.getYear(),date.getMonth(),(date.getDate()+offset));
		
		return newDate.getTime();
	}

}
