package ch.unibe.unisportbern.views.dialogs;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.parse.ParseMethodes;
import ch.unibe.unisportbern.support.DBMethodes;
import ch.unibe.unisportbern.support.SignManager;
import ch.unibe.unisportbern.views.MainActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.signup_layout, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(view);

		builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText username = (EditText) view.findViewById(R.id.editTextUsername);
				EditText password = (EditText) view.findViewById(R.id.editTextPW);
				ParseMethodes parse = new ParseMethodes (getActivity());
				
				SignManager manager = new SignManager(getActivity());
				manager.isDuplicateUsername(username.getText().toString(), password.getText().toString());
				
				parse.signingUp(username.getText().toString(), password.getText().toString());
				
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getActivity(), R.string.SignUpCancelToastText, Toast.LENGTH_LONG).show();
				SignUpDialog.this.getDialog().cancel();
				getActivity().finish();
			}
		});
		
		return builder.create();
	}

}
