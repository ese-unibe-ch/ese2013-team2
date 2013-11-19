package ch.unibe.unisportbern.support;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;

public class JsonCoordinate{

	private String coordinate;
	private String URL;
	
	public JsonCoordinate(){
		this.coordinate = "";
	}
	
	public String getCoordinate(Course course) throws JSONException, InterruptedException, ExecutionException, TimeoutException{		
		this.URL = "http://scg.unibe.ch/ese/unisport/location.php?loc=Turnhalle%20B";
		JsonHelper jsonHelper = new JsonHelper(this.URL);
		
		jsonHelper.executeJson();
		
		this.coordinate = jsonHelper.getObject().getString("lat")+","+jsonHelper.getObject().getString("lon");

		return this.coordinate;
	}
}
