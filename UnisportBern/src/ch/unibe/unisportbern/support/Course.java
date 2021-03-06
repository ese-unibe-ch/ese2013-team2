package ch.unibe.unisportbern.support;

public class Course implements IEvent{

	private int cid;
	private Sport sport;
	private String courseName;
	private String day;
	private String time;
	private String phases;
	private String location;
	private String information;
	private boolean subscriptionRequired;
	private String kew;
	
	private String imageLink;
	
	public Course (int cid, Sport sport, String courseName, String day, String time, String phases, String location, String information, boolean subscriptionRequired, String kew, String imageLink){
		this.cid = cid;
		this.sport = sport;
		this.courseName = courseName;
		this.day = day;
		this.time = time;
		this.phases = phases;
		this.location = location;
		this.information = information;
		this.subscriptionRequired = subscriptionRequired;
		this.kew = kew;
		this.imageLink = imageLink;
	}
	
	public String toString(){
		return courseName + " " + day +  " ("+getTime()+")";
	}
	
	@Override
	public Sport getSport(){
		return sport;
	}
	
	public int getId(){
		return cid;
	}
	
	public String getName(){
		return courseName;
	}
	
	public String getDay(){
		return day;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getStartTime(){
		return time.substring(0, 5);
	}
	
	public String getPhases(){
		return phases;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getInformation(){
		String furtherInformation = "Further Information: ";
		if(!this.information.isEmpty()) return furtherInformation+this.information;
		else return information;
	}
	
	public String getImageLink(){
		if(!this.imageLink.isEmpty()) this.imageLink.replace(" ","");
		else this.imageLink = "http://www.berkowits.in/Upload/ProductImageTh/31_634257293270063750_NO%20IMAGE.jpg";
		
		return this.imageLink;
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
	
	/**
	 * 
	 * @return
	 */
	public String getNextDay(){
		Day day = new Day(this.day);
		return day.getNextDay();
	}

	/**
	 * 
	 * @return a string representing the time a reminder is set or "no reminder" if there's no reminder
	 */
	public String getReminder() {
		return "";
	}
}
