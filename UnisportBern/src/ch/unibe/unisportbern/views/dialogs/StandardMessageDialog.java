package ch.unibe.unisportbern.views.dialogs;

import ch.unibe.unisportbern.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class StandardMessageDialog extends DialogFragment{

	private View view;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getActivity().getLayoutInflater();
		view = inflater.inflate(R.layout.standard_message_dialog_layout, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(view);

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				StandardMessageDialog.this.getDialog().cancel();
			}
		});
		
		return builder.create();
	}


	public void show(FragmentManager manager, String tag, String title, String text){
		super.show(manager, tag);
		
		TextView titleView = (TextView) view.findViewById(R.id.textViewMessageTitle);
		TextView textView = (TextView) view.findViewById(R.id.textViewMessageText);
		
		titleView.setText(title);
		textView.setText(text);
		
	}
}
