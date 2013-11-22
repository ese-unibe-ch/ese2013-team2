package ch.unibe.unisportbern.views.dialogs;


import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.views.details.reminder.BootReceiver;
import ch.unibe.unisportbern.views.details.reminder.Reminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ReminderDialog extends DialogFragment {
	
	private int min = -1;
	private String time;
	private String date;
	private String sportsName;
	private Spinner spinner;
	
	
	@Override
	public Dialog onCreateDialog(Bundle SavedInstanceState) {
		time = getArguments().getString("time");
		date = getArguments().getString("date");
		sportsName = getArguments().getString("sports");
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_layout_spinner, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(view);
		this.setUpSpinner(view);
		this.saveSelectedItem();
		
		builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Reminder rem = new Reminder();
				switch (spinner.getSelectedItemPosition()){
				case 0: min = -1;
				        break;
				case 1: min= 120;
		                break;
		        case 2: min = 90;
		                break;
		        case 3: min = 60;
		                break;
		        case 4: min = 30;
		        	    break;
		        case 5: min = 15;
		                break;
				case 6: min = 0;
				        break;
				case 7: min = -1;
		        break;
				}
				Intent intent = new Intent (getActivity(), BootReceiver.class);
				rem.saveToReminderIntent(time, date, intent, min, sportsName, getActivity());
				retrieveSelectedItem();
				getActivity().sendBroadcast(intent);
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						ReminderDialog.this.getDialog().cancel();
					}
				});
		return builder.create();
	}

	private void setUpSpinner(View view) {
		spinner = (Spinner) view.findViewById(R.id.spinnerReminderDialog);
		
		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.SpinnerReminderArray, android.R.layout.simple_spinner_item);
		
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(spinnerAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	 private void saveSelectedItem(){
		SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName",0);
		int spinnerValue = sharedPref.getInt("userChoiceSpinner",-1);
		if(spinnerValue != -1) 
		// set the value of the spinner 
		spinner.setSelection(spinnerValue);
		 
	 }
	 private void retrieveSelectedItem(){
			int userChoice = spinner.getSelectedItemPosition();
			SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName",0);
			SharedPreferences.Editor prefEditor = sharedPref.edit();
			prefEditor.putInt("userChoiceSpinner",userChoice);
			prefEditor.commit();
		 
	 }
}
