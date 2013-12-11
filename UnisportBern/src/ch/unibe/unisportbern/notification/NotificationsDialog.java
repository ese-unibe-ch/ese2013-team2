package ch.unibe.unisportbern.notification;

import java.util.ArrayList;

import ch.unibe.unisportbern.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class NotificationsDialog extends DialogFragment{

	private ArrayList<INotification> notifications;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.notifications_dialog_layout, null);
		
		NotificationManager manager = new NotificationManager(getActivity());
			
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(view);
		
		builder.setPositiveButton("Ok", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		return builder.create();
		
	}
}
