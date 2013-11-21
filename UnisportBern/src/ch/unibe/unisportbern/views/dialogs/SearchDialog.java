package ch.unibe.unisportbern.views.dialogs;

import ch.unibe.unisportbern.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class SearchDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View view = getActivity().getLayoutInflater().inflate(R.layout.search_dialog_layout, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(view);
		builder.setPositiveButton("GO!", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

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

	private void setupRadioButtons(final View view) {
		final RadioButton rbName = (RadioButton) view.findViewById(R.id.radioButtonSearchName);
		final RadioButton rbTime = (RadioButton) view.findViewById(R.id.radioButtonSearchTime);

		rbName.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// swap state of other radiobutton?
				if (rbName.isChecked()) {
					hideSpinners(view);
					showEditText(view);
				}
				if (rbTime.isChecked()) {
					showSpinners(view);
					hideEditText(view);
				}

			}

		});
	}
	
	private void showSpinners(View view) {
		Spinner spinnerDay = (Spinner) view.findViewById(R.id.spinnerSearchDay);
		Spinner spinnerTime = (Spinner) view.findViewById(R.id.spinnerSearchTime);

		spinnerDay.setVisibility(0);
		spinnerTime.setVisibility(0);

	}
	
	private void hideSpinners(View view) {
		Spinner spinnerDay = (Spinner) view.findViewById(R.id.spinnerSearchDay);
		Spinner spinnerTime = (Spinner) view.findViewById(R.id.spinnerSearchTime);

		spinnerDay.setVisibility(2);
		spinnerTime.setVisibility(2);

	}

	private void showEditText(View view) {
		EditText et = (EditText) view.findViewById(R.id.editTextSearchName);

		et.setVisibility(0);

	}

	private void hideEditText(View view) {
		EditText et = (EditText) view.findViewById(R.id.editTextSearchName);

		et.setVisibility(2);

	}
}
