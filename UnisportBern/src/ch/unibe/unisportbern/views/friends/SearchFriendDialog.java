package ch.unibe.unisportbern.views.friends;

import ch.unibe.unisportbern.R;
import ch.unibe.unisportbern.support.CustomOnClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class SearchFriendDialog extends DialogFragment {

	View view;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		view = inflater.inflate(R.layout.search_friend_dialog_layout, null);

		builder.setView(view);

		builder.setPositiveButton("Ok", new CustomOnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO: 

			}
		});

		Button searchButton = (Button) view.findViewById(R.id.buttonSearchFriend);
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO search friend and add him to the layout below
				// change visibilities
				
			}
		});
		
		Button okButton = (Button) view.findViewById(R.id.buttonSearchFriendResultOk);
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO add as friend
				TextView tv = (TextView) view.findViewById(R.id.TextViewFriendNameResult);
			}
		});
		
		return builder.create();
	}

}
