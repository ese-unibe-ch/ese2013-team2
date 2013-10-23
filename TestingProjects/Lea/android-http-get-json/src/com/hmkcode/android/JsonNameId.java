package com.hmkcode.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JsonNameId extends AsyncTask<String, Void, String> {

	private ArrayList<String> allsportnames = new ArrayList<String>(140);
	private ArrayList<Integer> allsportid = new ArrayList<Integer>(140);
	private ArrayList<Sport> allSports = new ArrayList<Sport>(140);

	@Override
	protected String doInBackground(String... urls) {

		return GET(urls[0]);
	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		// WICHTIGER CODEBLOCK!!
		try {
			JSONObject json = new JSONObject(result);

			for (int i = 0; i < 137; i++) {
				String index = Integer.toString(i + 1);
				String currentsport;
				currentsport = json.getJSONObject("result").getString(index);
				allsportnames.add(currentsport);
				allsportid.add((i + 1));
				allSports.add(new Sport(allsportid.get(i), allsportnames.get(i)));

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * returns a list of all sports containing their name and id
	 * @return
	 */
	public ArrayList<Sport> getAllSportsIdName() {
		return allSports;
	}

	private static String GET(String url) {
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;

	}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}
}
