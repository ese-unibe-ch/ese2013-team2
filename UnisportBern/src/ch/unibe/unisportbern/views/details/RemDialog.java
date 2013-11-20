package ch.unibe.unisportbern.views.details;

import ch.unibe.unisportbern.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RemDialog extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle SavedInstanceState) {
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_layout_spinner, null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setView(view);
		
		builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						RemDialog.this.getDialog().cancel();
					}
				});
		
		setUpSpinner(view);
		
		return builder.create();
	}

	private void setUpSpinner(View view) {
		Spinner spinner = (Spinner) view.findViewById(R.id.spinnerReminderDialog);
		
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
}
