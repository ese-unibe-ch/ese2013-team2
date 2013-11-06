package com.example.listradiobutton2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends Activity {
	
	RadioGroup timeOptions;
	Button confirm;
	RadioButton radioButtonSelected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio);
		addListenerOnButton();
	}
	
	  public void addListenerOnButton() {
		  
			timeOptions = (RadioGroup) findViewById(R.id.radioSex);
			confirm = (Button) findViewById(R.id.btnDisplay);
		 
			confirm.setOnClickListener(new OnClickListener() {
		 
				@Override
				public void onClick(View v) {
		 
				        // get selected radio button from radioGroup
					int selectedId = timeOptions.getCheckedRadioButtonId();
		 
					// find the radiobutton by returned id
				        radioButtonSelected = (RadioButton) findViewById(selectedId);
		 
					Toast.makeText(OptionsActivity.this,
						radioButtonSelected.getText(), Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent (OptionsActivity.this, BootReceiver.class);
					sendBroadcast(intent);
				}
		 
			});
		 
		  }
	
	

}
