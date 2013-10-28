package ch.unibe.unisportbern.support;

public class Course {

	int sid;
	String courseName;
	String day;
	String time;
	boolean[] phases;
	String location;
	String information;
	boolean subscriptionRequired;
	String kew;
	
	public Course (int sid,String courseName, String day, String time, boolean[] phases, String location, String information, boolean subscriptionRequired, String kew){
		this.sid = sid;
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
}
