package ch.unibe.unisportbern.support;

public class Course {

	// TODO: create class
	int cid;
	Sport sport;
	Date date;
	boolean[] phases;
	String location;
	String information;
	boolean subscriptionRequired;
	int kew;
	
	public Course (int cid,Sport sport, Date date, boolean[] phases, String location, String information, boolean subscriptionRequired, int kew){
		this.cid = cid;
		this.sport = sport;
		this.date = date;
		this.phases = phases;
		this.location = location;
		this.information = information;
		this.subscriptionRequired = subscriptionRequired;
		this.kew = kew;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return cid;
	}
	
}
