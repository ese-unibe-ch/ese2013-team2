package ch.unibe.unisportbern.support;

public class Course {

	//int sid;
	Sport sport;
	String courseName;
	String day;
	String time;
	boolean[] phases;
	String location;
	String information;
	boolean subscriptionRequired;
	String kew;
	
	public Course (Sport sport, String courseName, String day, String time, boolean[] phases, String location, String information, boolean subscriptionRequired, String kew){
		//this.sid = sid;
		this.sport = sport;
		this.courseName = courseName;
		this.day = day;
		this.time = time;
		this.phases = phases;
		this.location = location;
		this.information = information;
		this.subscriptionRequired = subscriptionRequired;
		this.kew = kew;
	}
	
	public String toString(){
		return courseName;
	}
	
	public Sport getSport(){
		return sport;
	}
	
	/*public int getId(){
		return sid;
	}*/
	
	public String getName(){
		return courseName;
	}
	
	public String getDay(){
		return day;
	}
	
	public String getTime(){
		return time;
	}
	
	public boolean[] getPhases(){
		return phases;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getInfo(){
		return information;
	}
	
	/**
	 * @return true if a subscribtion is required for attending the course
	 * @return
	 */
	public boolean getSub(){
		return subscriptionRequired;
	}
	
	public String getKew(){
		return kew;
	}
}
