package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonCourse {

	private ArrayList<Course> allCourses;
	private String URL = "http://scg.unibe.ch/ese/unisport/sport.php?id=";
	
	public JsonCourse(){
		allCourses = new ArrayList<Course>();
	}
	
	public ArrayList<Course> getAllCourses(Sport sport) throws JSONException, InterruptedException, ExecutionException, TimeoutException{		
		
		String sportName = sport.getName();
		
		JsonHelper jsonHelper = new JsonHelper(URL+Integer.toString(sport.getId()));
		
		jsonHelper.executeJson();
		
		JSONArray array = jsonHelper.getObject().getJSONArray(sportName);
		

		for(int i = 0; i < array.length(); i++)
		{
			JSONObject row = array.getJSONObject(i);

			allCourses.add(new Course(i,sport,row.getString("course"),row.getString("day"),row.getString("time"),row.getString("period"),row.getString("place"),row.getString("info"), subscriptionRequired(row.getString("subscription")), row.getString("kew")));
		}
		return allCourses;
	}
	
	private boolean subscriptionRequired(String required){
		boolean isRequired;
		if(required.equals("null")) isRequired = false;
		else isRequired = true;
		
		return isRequired;
	}
}
