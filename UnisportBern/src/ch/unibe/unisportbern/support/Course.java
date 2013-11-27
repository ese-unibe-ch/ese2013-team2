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
//<<<<<<< HEAD
	private String url;
//=======
	private String imageLink;
//>>>>>>> 4e63ad33e5ad358e9a368674ecd9c00bf7b530fe
	
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
//<<<<<<< HEAD
		this.url = url;
//=======
		this.imageLink = imageLink;
//>>>>>>> 4e63ad33e5ad358e9a368674ecd9c00bf7b530fe
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
		return information;
	}
	
	public String getImageLink(){
		if(this.imageLink != null) this.imageLink.replace(" ","");
		else this.imageLink = "";
		
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

	public String getUrl() {
		return url;
	}
}
