package com.hmkcode.android;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.EditText;
import android.app.Activity;

public class MainActivity extends Activity {

	private EditText etResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// get reference to the views
		etResponse = (EditText) findViewById(R.id.etResponse);
		
		etResponse.setText("it works1!");
		// call AsynTask to perform network operation on separate thread
		GetJson json = new GetJson();
		json.execute();
		etResponse.setText("it works2!");
		// new HttpAsyncTask().getAllSports();
		
		displayList(json.getSports());
	}
	
	private void displayList(ArrayList<Sport> allSports){
		etResponse.setText("");
		
		for (Sport s : allSports){
			etResponse.setText(etResponse.getText() + s.getName() + "/n");
		}
	}

}
