
package ch.unibe.unisportbern.support;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import ch.unibe.unisportbern.views.dialogs.StandardMessageDialog;
import android.content.Context;

public class JsonCoordinate{

	private String coordinate;
	private String URL;
	private Context context;
	
	public JsonCoordinate(Context context){
		this.coordinate = "";
		this.context = context;
		this.URL = "http://scg.unibe.ch/ese/unisport/location.php?loc=";
	}
	
	/**
	 * Get the coordinate of a certain course for Google-Maps. Uses the JsonHelper-Class to execute Json requests
	 * @param course
	 * @return coordinates of a certain course
	 * @throws JSONException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	public String getCoordinate(Course course) throws JSONException, InterruptedException, ExecutionException, TimeoutException{		
		Network network = new Network(this.context);
		
		if(network.isOnline()){
			JsonHelper jsonHelper = new JsonHelper(this.URL+course.getLocation());
			
			jsonHelper.executeJson();

			try {
				this.coordinate = jsonHelper.getObject().getString("lat")+","+jsonHelper.getObject().getString("lon");	
			} catch (Exception e) {
				StandardMessageDialog dialog = new StandardMessageDialog();
				dialog.showDialog(context, "API-Error", "Sorry! The API from the Assistent returned an invalid value. We couldn't calculate a route.", "invalid value");
				this.coordinate = course.getLocation();
			}
		}
		return this.coordinate;
	}
}
