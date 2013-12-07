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
import android.widget.TextView;

public class NotificationsDialog extends DialogFragment{

	ArrayList<INotification> notifications;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.notifications_dialog_layout, null);
		
		notifications = new ArrayList<INotification>();
		
		//TODO: get notifications
		
		if(notifications.isEmpty()){
			
			TextView tv = (TextView) view.findViewById(R.id.textViewNotificationDialogEmptyListText);
			tv.setText("There are no new Notifications available");
			tv.setVisibility(View.VISIBLE);
		}
			
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setView(view);
		
		builder.setPositiveButton("Ok", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO: additional behavior goes here
			}
		});
		
		return builder.create();
		
	}
}