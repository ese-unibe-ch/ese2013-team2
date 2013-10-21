package ch.unibe.unisportbern.support;

public class Date {

	long startTimeSec;
	long endTimeSec;
	String day;
	
	public Date(long startT, long endT, String d){
		startTimeSec = startT;
		endTimeSec = endT;
		day = d;
	}
	
	/**
	 *  display date in format: 'mo 20:15'
	 */
	@Override
	public String toString(){
		return day + " " + format(startTimeSec);	
	}

	private String format(long startTime) {
		// TODO format time to usual format: 20:30
		return "" + startTime;
	}
	
}
