package ch.unibe.unisportbern.views.dialogs;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.SignManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * This class is responsible for the dialog that appears when one has to register or login for the app.
 * @author Thomas Steinmann
 *
 */

public class SignUpLogInDialog extends DialogFragment {

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
				SignManager manager = new SignManager(getActivity());
				manager.saveAndlogIn(username.getText().toString(), password.getText().toString());
				
			}
		});
		
		return builder.create();
	}

}
