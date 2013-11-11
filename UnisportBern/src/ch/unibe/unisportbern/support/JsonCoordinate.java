package ch.unibe.unisportbern.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

public class JsonCoordinate extends AsyncTask<Void, Void, Void> {

	private String coordinate;
	private final static String COORDINATE = "scg.unibe.ch/ese/unisport/location.php?loc=";
	private String URL;
	private JSONObject objCoordinate;
	
	public JsonCoordinate(){
		this.coordinate = "";
	}

	protected Void doInBackground(Void... params) {

		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(new HttpGet(URL));
			InputStream is = response.getEntity().getContent();
			String inputStream = convertInputStreamToString(is);

			JSONObject json = new JSONObject(inputStream);
			objCoordinate = json.getJSONObject("result");

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
		this.get();
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
	
	public String getCoordinate(Course course) throws JSONException, InterruptedException, ExecutionException, TimeoutException{		
		this.URL = "http://scg.unibe.ch/ese/unisport/location.php?loc=Turnhalle%20B";
		
		this.executeJson();
		
		this.coordinate = objCoordinate.getString("lat")+","+objCoordinate.getString("lon");

		return this.coordinate;
	}
}
