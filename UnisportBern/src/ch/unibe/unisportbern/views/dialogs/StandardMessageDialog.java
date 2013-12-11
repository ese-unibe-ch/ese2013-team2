package ch.unibe.unisportbern.views.dialogs;

import ch.unibe.unisportbern.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class StandardMessageDialog extends DialogFragment {

	private View view;
	private String title;
	private String message;
	private String tag;
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		generateView();
		
		Bundle bundle = getArguments();
		title = bundle.getString("title");
		message = bundle.getString("message");

		TextView tvTitle = (TextView) view.findViewById(R.id.textViewMessageTitle);
		TextView tvMessage = (TextView) view.findViewById(R.id.textViewMessageText);
		
		tvTitle.setText(title);
		tvMessage.setText(message);
		
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
	
	public void showDialog(Context context, String title, String message, String tag){
		
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		bundle.putString("message", message);
		
		this.setArguments(bundle);
		this.show(((Activity)context).getFragmentManager(), tag);
	}

	private void generateView() {
		if (view == null) {
			LayoutInflater inflater = getActivity().getLayoutInflater();
			view = inflater.inflate(R.layout.standard_message_dialog_layout, null);
		}
	}
}
