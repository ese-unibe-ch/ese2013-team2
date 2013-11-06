package ch.unibe.unisportbern.support;

import java.io.IOException;
import java.util.List;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class Course {

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
	
	public Course (int cid, Sport sport, String courseName, String day, String time, String phases, String location, String information, boolean subscriptionRequired, String kew){
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
	}
	
	public String toString(){
		return courseName+" ("+getTime()+")";
	}
	
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
	
	public String getPhases(){
		return phases;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getInformation(){
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
	
	public String getCoordinate(Context context) throws IOException{
		
		Geocoder geocoder = new Geocoder(context);  
		List<Address> addresses;
		addresses = geocoder.getFromLocationName(this.location, 1);
		    
		double latitude= addresses.get(0).getLatitude();
		double longitude= addresses.get(0).getLongitude();
		
		return Double.toString(latitude)+","+Double.toString(longitude);
	}
}
