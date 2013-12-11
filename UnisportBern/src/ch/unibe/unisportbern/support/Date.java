package ch.unibe.unisportbern.support;

/**
 * is responsible for giving a format of the date.
 * 
 * @author Michael Scheuerer
 */

public class Date {

	private long startTimeSec;
	private long endTimeSec;
	private String day;
	
	public Date(long startT, long endT, String d){
		startTimeSec = startT;
		endTimeSec = endT;
		day = d;
	}
	
	/**
	 *  display date in format: 'mo 20:15'
	 */
	public String toString(){
		return day;	
	}
	
}
