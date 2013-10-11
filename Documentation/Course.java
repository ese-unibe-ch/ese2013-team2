
public class Course {

	private int id;
	private String day;
	private int startTime;
	private int endTime;
	private boolean[] phases;
	private String name;
	private String place;
	private String info;
	private boolean subscription;
	private int kew;
	
	public int getId(){
		return id;
	}
	
	public String getDay(){
		return day;
	}
	
	public int getStartTime(){
		return startTime;
	}
	
	public int getEndTime(){
		return endTime;
	}
	
	public boolean[] getPhases(){
		return phases;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPlace(){
		return place;
	}
	
	public String getInfo(){
		return info;
	}
	
	public boolean getSubscription(){
		return subscription;
	}
	
	public int getKew(){
		return kew;
	}
}
