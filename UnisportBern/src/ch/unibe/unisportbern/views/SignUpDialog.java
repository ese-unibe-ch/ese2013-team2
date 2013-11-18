package ch.unibe.unisportbern.views;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.views.details.RemDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class SignUpDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.signup_layout, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(view);

		builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Store Username and PW to database
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				SignUpDialog.this.getDialog().cancel();
			}
		});
		
		return builder.create();
	}

}
