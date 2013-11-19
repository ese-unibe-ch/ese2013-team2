package ch.unibe.unisportbern.support;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;

public class JsonSport {

	private ArrayList<Sport> allSports;
	private String URL = "http://scg.unibe.ch/ese/unisport/sports.php";
	
	public JsonSport(){
		allSports = new ArrayList<Sport>();
	}
	
	public ArrayList<Sport> getAllSports() throws JSONException, InterruptedException, ExecutionException, TimeoutException{
		JsonHelper jsonHelper = new JsonHelper(this.URL);
		
		jsonHelper.executeJson();	
		String currentsport;

		for (int i = 1; i <= jsonHelper.getObject().length(); i++) {
			String index = Integer.toString(i);
			currentsport = jsonHelper.getObject().getString(index);
			this.allSports.add(new Sport(i+1, currentsport));
		}
			
			
		return this.allSports;	
	}
}
