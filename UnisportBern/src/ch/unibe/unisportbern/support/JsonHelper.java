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

public class JsonHelper extends AsyncTask<Void, Void, Void>{
	private String SPORTS;
	private JSONObject obj;
	
	public JsonHelper(String url){
		this.SPORTS = url; 
	}

	@Override
	protected Void doInBackground(Void... params) {

		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(new HttpGet(SPORTS));
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
	
	public void executeJson() throws JSONException, InterruptedException, ExecutionException, TimeoutException {
		this.execute();
		this.get();
	}
	
	public JSONObject getObject(){
		return this.obj;
	}
}
