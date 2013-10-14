package com.hmkcode.android;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
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
import org.json.*;

public class MainActivity extends Activity{

	Sport [] allsports = new Sport [138];
	EditText etResponse;
	TextView tvIsConnected;
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


		// call AsynTask to perform network operation on separate thread
		new HttpAsyncTask().execute("http://scg.unibe.ch/ese/unisport/sports.php");
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
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
    	
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
            	//eigentlich würde ich hier gerne ein Sportobjekt erzeugen:
            	//Sport [] allsports = new Sport [138]; 
            	
            	String [] allsportnames = new String [138];
            	int [] allsportid = new int [138];
            	
            	String str = "";
            	for(int i=1; i < 138; i++){
            		String index = Integer.toString(i);
            		String currentsport;
            		currentsport = json.getJSONObject("result").getString(index);
            		//Hier sollte noch für allsports[i] name & id gesettet werden
            		allsportnames[i] = currentsport;
            		allsportid[i] = i;
            		str +=  currentsport +  "\n";
            		etResponse.setText(currentsport);
            	}
            	etResponse.setText(str);
            	

            } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
       }
    }
        

        
}