package ch.unibe.unisportbern.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

public class JsonCourse extends AsyncTask<Void, Void, Void> {

	private ArrayList<Course> allCourses;
	private final static String COURSE = "http://scg.unibe.ch/ese/unisport/sport.php?id=";
	private String URL;
	private JSONObject objCourse;
	
	public JsonCourse(){
		allCourses = new ArrayList<Course>();
	}

	protected Void doInBackground(Void... params) {

		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(new HttpGet(URL));
			InputStream is = response.getEntity().getContent();
			String inputStream = convertInputStreamToString(is);

			JSONObject json = new JSONObject(inputStream);
			objCourse = json.getJSONObject("result");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public void executeJson() throws JSONException, InterruptedException, ExecutionException, TimeoutException {
		this.execute();
		this.get(200,TimeUnit.MILLISECONDS);
	}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
		new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;
		inputStream.close();
		return result;
	}
	
	public ArrayList<Course> getAllCourses(Sport sport) throws JSONException, InterruptedException, ExecutionException, TimeoutException{		
		URL = COURSE+Integer.toString(sport.getId());
		
		String sportName = sport.getName();
		
		this.executeJson();
		
		JSONArray array = objCourse.getJSONArray(sportName);
		

		for(int i = 0; i < array.length(); i++)
		{
			JSONObject row = array.getJSONObject(i);

			allCourses.add(new Course(i, sport,row.getString("course"),row.getString("day"),row.getString("time"),getPeriode(row.getString("period")),row.getString("place"),row.getString("info"), subscriptionRequired(row.getString("subscription")), row.getString("kew")));
		}
		return allCourses;
	}
	
	private boolean[] getPeriode(String periode){
		boolean[] substring = new boolean[5];
		int b=0;
		for(int i=0;i<9;i+=2){
			if(periode.substring(i,i+1).equals("-")){
				substring[b] = false;
			}
			else{
				substring[b] = true;
			}
			b++;
		}
		return substring;
	}
	
	private boolean subscriptionRequired(String required){
		boolean isRequired;
		if(required.equals("null")) isRequired = false;
		else isRequired = true;
		
		return isRequired;
	}
}
