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

public class Json extends AsyncTask<Void, Void, Void> {

	private ArrayList<Sport> allSports;
	private final static String SPORTS = "http://scg.unibe.ch/ese/unisport/sports.php";
	private final static String COURSE = "http://scg.unibe.ch/ese/unisport/sport.php?id=";
	private String URL;
	private JSONObject obj;
	
	public Json(){
		allSports = new ArrayList<Sport>();
	}

	protected Void doInBackground(Void... params) {

		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(new HttpGet(URL));
			InputStream is = response.getEntity().getContent();
			String inputStream = convertInputStreamToString(is);

			JSONObject json = new JSONObject(inputStream);
			obj = json.getJSONObject("result");
			
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
		this.get(300,TimeUnit.MILLISECONDS);
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
	
	public ArrayList<Sport> getAllSports() throws JSONException, InterruptedException, ExecutionException, TimeoutException{
		URL = SPORTS;
		this.executeJson();
		String currentsport;
		
		for (int i = 1; i < obj.length(); i++) {
			String index = Integer.toString(i);
			currentsport = obj.getString(index);
			allSports.add(new Sport(i, currentsport));
		}
		return allSports;
	}
 
	/* public ArrayList<String[]> getAllCourses(int sid) throws JSONException, InterruptedException, ExecutionException, TimeoutException{		
		
		String sportName = getAllSports().get(sid).getName();
		
		URL = COURSE+Integer.toString(sid);
		this.executeJson();
		
		JSONArray array = obj.getJSONArray(sportName);
		
		ArrayList<String[]> getBack = new ArrayList<String[]>();
		String[] temp = new String[8];

		for(int i = 0; i < array.length(); i++)
		{
			JSONObject row = array.getJSONObject(i);

			temp[0] = row.getString("course");
			temp[1] = row.getString("day");
			temp[2] = row.getString("time");
			temp[3] = row.getString("period");
			temp[4] = row.getString("place");
			temp[5] = row.getString("info");
			temp[6] = row.getString("subscription");
			temp[7] = row.getString("kew");
			
			getBack.add(i,temp);
		}
		return getBack;
	}*/
}
