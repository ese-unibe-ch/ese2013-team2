package com.hmkcode.android;

import java.util.ArrayList;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;


public class MainActivity extends Activity{

	
	EditText etResponse;
	TextView tvIsConnected;
	
	
	public ArrayList <String>allsportnames = new ArrayList<String>(140);
	public ArrayList <Integer>allsportid = new ArrayList<Integer>(140);
	public ArrayList <Sport>allSports = new ArrayList<Sport>(140);
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// get reference to the views
		etResponse = (EditText) findViewById(R.id.etResponse);
		tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

		// check if you are connected or not
		if(isConnected()){
			tvIsConnected.setBackgroundColor(0xFF00CC00);
			tvIsConnected.setText("You are connected");
        }
		else{
			tvIsConnected.setText("You are NOT connected");
		}

		etResponse.setText("it works1!");
		// call AsynTask to perform network operation on separate thread
		new HttpAsyncTask().execute("http://scg.unibe.ch/ese/unisport/sports.php");
		etResponse.setText("it works2!");
		//new HttpAsyncTask().getAllSports();
	}

	public static String GET(String url){
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
			if(inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        
        inputStream.close();
        return result;
        
    }

    public boolean isConnected(){
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
    	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	    if (networkInfo != null && networkInfo.isConnected()) 
    	    	return true;
    	    else
    	    	return false;	
    }
    
    
    
public class HttpAsyncTask extends AsyncTask<String, Void, String> {
    	

        @Override
        protected String doInBackground(String... urls) {
              
            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
            //WICHTIGER CODEBLOCK!!
            try {
            	JSONObject json = new JSONObject(result);
            	
            	String str = "";
            	for(int i=0; i < 137; i++){
            		String index = Integer.toString(i+1);
            		String currentsport;
            		currentsport = json.getJSONObject("result").getString(index);
            		str += currentsport + "\n";
            		allsportnames.add(currentsport);
            		allsportid.add((i+1));
            		allSports.add(new Sport(allsportid.get(i), allsportnames.get(i)));
            		
            	}
            	etResponse.setText(str);
            	

            } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
       }
    
   
    public ArrayList<Sport> getAllSports(){
    	ArrayList <Sport>allSports = new ArrayList<Sport>(140);
    	etResponse.setText("getAllSports start");
    	for(int i=0; i < 137; i++){
    		allSports.add(new Sport(allsportid.get(i), allsportnames.get(i)));
    		etResponse.setText("it works!");
    	}
    	etResponse.setText("it works!");
    	return allSports;
    }
    

    
    


        
    }    
}