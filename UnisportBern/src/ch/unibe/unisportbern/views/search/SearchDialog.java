package ch.unibe.unisportbern.views.search;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import ch.unibe.unisportbern.R;

public class SearchDialog extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final View view = getActivity().getLayoutInflater().inflate(R.layout.search_dialog_layout, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(view);
		builder.setPositiveButton("GO!", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Intent results = createSearchResult(view);
				sendResult(results);
			}
		});

		builder.setNegativeButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				SearchDialog.this.getDialog().cancel();

			}
		});

		setupRadioButtons(view);
		
		return builder.create();
	}
	
	private void sendResult(Intent intent){
		getTargetFragment().onActivityResult(0, 0, intent);
	}
	
	private void setupRadioButtons(final View view) {
		final RadioButton rbName = (RadioButton) view.findViewById(R.id.radioButtonSearchName);
		final RadioButton rbTime = (RadioButton) view.findViewById(R.id.radioButtonSearchTime);

		rbName.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					showEditText(view);
					rbTime.setChecked(false);
				}
				else hideEditText(view);
			}
		});
		
		rbTime.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					showSpinners(view);
					rbName.setChecked(false);
				}
				else hideSpinners(view);
				
			}
		});
		
		
		}
	
	private Intent createSearchResult(View view) {
		RadioButton rbName = (RadioButton) view.findViewById(R.id.radioButtonSearchName);
		RadioButton rbTime = (RadioButton) view.findViewById(R.id.radioButtonSearchTime);
		
		EditText et = (EditText) view.findViewById(R.id.editTextSearchName);
		
		Spinner spinnerDay = (Spinner) view.findViewById(R.id.spinnerSearchDay);
		Spinner spinnerTime = (Spinner) view.findViewById(R.id.spinnerSearchTime);
		
		Intent intent = new Intent();
		SearchHandler handler;
		
		if (rbName.isChecked()){
			handler = new SearchHandler(0, et.getText().toString(), -1, -1);
			intent.putExtra("parameter", handler);
		}
		else if(rbTime.isChecked()){
			handler = new SearchHandler(1, null, spinnerDay.getSelectedItemPosition(), spinnerTime.getSelectedItemPosition());
			intent.putExtra("parameter", handler);
		}
		
		return intent;
	}
	
	private void showSpinners(View view) {
		Spinner spinnerDay = (Spinner) view.findViewById(R.id.spinnerSearchDay);
		Spinner spinnerTime = (Spinner) view.findViewById(R.id.spinnerSearchTime);

		spinnerDay.setVisibility(View.VISIBLE);
		spinnerTime.setVisibility(View.VISIBLE);

	}
	
	private void hideSpinners(View view) {
		Spinner spinnerDay = (Spinner) view.findViewById(R.id.spinnerSearchDay);
		Spinner spinnerTime = (Spinner) view.findViewById(R.id.spinnerSearchTime);

		spinnerDay.setVisibility(View.GONE);
		spinnerTime.setVisibility(View.GONE);

	}

	private void showEditText(View view) {
		EditText et = (EditText) view.findViewById(R.id.editTextSearchName);
		et.setVisibility(View.VISIBLE);

	}

	private void hideEditText(View view) {
		EditText et = (EditText) view.findViewById(R.id.editTextSearchName);

		et.setVisibility(View.GONE);

	}
}
